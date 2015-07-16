package Production;


import Production.Factories.BlackQueenPieceFactory;
import Production.Factories.WhiteQueenPieceFactory;
import Production.Strategies.BoardSetup.BoardSetupStrategy;
import Production.Utility.AlgorithmUtility;
import Production.Utility.BoardPosition;
import Production.Utility.Color;
import Production.Utility.GameConstants;

import java.util.*;


public class GameImpl implements Game, Observable{

    private static final int TURNS_PLAYED_IN_ROUND_5 = 10;

    private Color _playerInTurn;
    private int _turnsPlayed;
    private Map<BoardPosition, Piece> _pieceMap;
    private BoardSetupStrategy _boardSetupStrategy;
    private Set<Observer> _observers;
    private boolean _isWhiteChecked;
    private boolean _isWhiteCheckMated;
    private boolean _isBlackChecked;
    private boolean _isBlackCheckMated;

    public GameImpl(BoardSetupStrategy boardSetupStrategy) {
        _boardSetupStrategy = boardSetupStrategy;
        _playerInTurn = Color.WHITE;
        _turnsPlayed = 0;
        _isWhiteChecked = false;
        _isWhiteCheckMated = false;
        _isBlackChecked = false;
        _isBlackCheckMated = false;
        _observers = new HashSet<Observer>();
        setupPieces();
    }

    private void setupPieces() {
        _pieceMap = new HashMap<BoardPosition, Piece>();

        _boardSetupStrategy.setupPieces(_pieceMap);
    }

    @Override
    public Color getWinner() {
        if (_turnsPlayed >= TURNS_PLAYED_IN_ROUND_5) {
            return Color.WHITE;
        }
        return Color.NONE;
    }

    @Override
    public Color getPlayerInTurn() {
        return _playerInTurn;
    }

    @Override
    public Piece getPieceAtPosition(BoardPosition p) {
        return _pieceMap.get(p);
    }

    // returns a shallow copy of the _pieceMap
    @Override
    public Map<BoardPosition, Piece> getPieceMap() {
        return new HashMap<BoardPosition, Piece>(_pieceMap);
    }

    @Override
    public boolean movePiece(BoardPosition from, BoardPosition to) {
        if (isMoveValid(from, to)) {
            // TODO: DELETE BELOW CONSOLE PRINTOUT
            // System.out.println("_game.movePiece(BoardPosition."+from+", BoardPosition."+to+");");

            performPieceMove(from, to, _pieceMap);
            swapPlayerTurn();
            _turnsPlayed++;

            _isWhiteChecked = AlgorithmUtility.isPlayerChecked(this, _pieceMap, Color.WHITE);
            _isWhiteCheckMated = AlgorithmUtility.isPlayerCheckMated(this, _pieceMap, Color.WHITE);
            _isBlackChecked = AlgorithmUtility.isPlayerChecked(this, _pieceMap, Color.BLACK);
            _isBlackCheckMated = AlgorithmUtility.isPlayerCheckMated(this, _pieceMap, Color.BLACK);

            // notify observers of changes
            notifyObservers();

            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public boolean isMoveValid(BoardPosition from, BoardPosition to) {
        Piece movingPiece = _pieceMap.get(from);

        // bail out
        if (movingPiece == null) return false;

        Iterator<BoardPosition> it = movingPiece.possibleMovingPositions(from, this, _pieceMap);
        boolean toPosIsValidMove = false;
        while (it.hasNext()) {
            BoardPosition currPos = it.next();
            if (currPos == to) {
                toPosIsValidMove = true;
            }
        }

        // simulate move on a temporary game copy (deep copy)
        Map<BoardPosition, Piece> tempMap = new HashMap<BoardPosition, Piece>();
        for (Map.Entry e : _pieceMap.entrySet()) {
            BoardPosition bp = (BoardPosition) e.getKey();
            Piece p = (Piece) e.getValue();
            if (p instanceof StatePieceImpl) {
                StatePieceImpl sp = (StatePieceImpl) p;
                p = (Piece) sp.clone();
            }
            tempMap.put(bp, p);
        }

        boolean isCheckAfterMoving = false;
        if (toPosIsValidMove) {
            performPieceMove(from, to, tempMap);
            isCheckAfterMoving = AlgorithmUtility.isPlayerChecked(this, tempMap, movingPiece.getColor());
        }

        // must not be checked after moving
        boolean noPlayersViolatesCheckRules = !isCheckAfterMoving;

        // if the player in turn is moving his own piece and
        // the target position is not already occupied, and
        // the player moving is not checked after moving,
        // the move is legal
        return (movingPiece.getColor() == getPlayerInTurn()) &&
                toPosIsValidMove &&
                noPlayersViolatesCheckRules;
    }

    public BoardPosition getPositionOfPiece(Piece piece) {
        for (BoardPosition bp : BoardPosition.values()) {
            if (getPieceAtPosition(bp) == piece) { return bp; }
        }
        return null;
    }

    // returns true if a player is in check
    public Color isCheck() {
        if (_isWhiteChecked) return Color.WHITE;
        if (_isBlackChecked) return Color.BLACK;
        return Color.NONE;
    }

    @Override
    public Color isPlayerInCheckMate() {
        if (_isWhiteCheckMated) return Color.WHITE;
        if (_isBlackCheckMated) return Color.BLACK;
        return Color.NONE;
    }

    private void performPieceMove(BoardPosition from, BoardPosition to, Map<BoardPosition, Piece> pMap) {
        Piece pieceToMove = pMap.get(from);
        pMap.put(to, pieceToMove);
        pMap.remove(from);

        // Special case handling: Castling (moving rook accordingly)
        boolean pieceToMoveIsWhiteKing = pieceToMove.getColor().equals(Color.WHITE) &&
                                         pieceToMove.getType().equals(GameConstants.KING);
        boolean pieceToMoveIsBlackKing = pieceToMove.getColor().equals(Color.BLACK) &&
                                         pieceToMove.getType().equals(GameConstants.KING);

        // W/B+K/R : White/Black King/Rook
        BoardPosition WK_initPos = BoardPosition.E1;
        BoardPosition WK_shortCastlingMoveTarget= BoardPosition.G1;
        BoardPosition WK_longCastlingMoveTarget= BoardPosition.C1;
        BoardPosition WR_shortInitPos = BoardPosition.H1;
        BoardPosition WR_shortCastlingMoveTarget = BoardPosition.F1;
        BoardPosition WR_longInitPos = BoardPosition.A1;
        BoardPosition WR_longCastlingMoveTarget = BoardPosition.D1;
        BoardPosition BK_initPos = BoardPosition.E8;
        BoardPosition BK_longCastlingMoveTarget= BoardPosition.C8;
        BoardPosition BK_shortCastlingMoveTarget= BoardPosition.G8;
        BoardPosition BR_shortInitPos = BoardPosition.H8;
        BoardPosition BR_shortCastlingMoveTarget = BoardPosition.F8;
        BoardPosition BR_longInitPos = BoardPosition.A8;
        BoardPosition BR_longCastlingMoveTarget = BoardPosition.D8;
        // in case of castling on either side, perform special actions below
        if (from == WK_initPos && to == WK_shortCastlingMoveTarget && pieceToMoveIsWhiteKing) {
            // White performing small castling(right side), move white rook (H1 to F1)
            Piece shortRook = pMap.get(WR_shortInitPos);
            pMap.put(WR_shortCastlingMoveTarget, shortRook);
            pMap.remove(WR_shortInitPos);
        }
        else if (from == WK_initPos && to == WK_longCastlingMoveTarget && pieceToMoveIsWhiteKing) {
            // White performing long castling(left side), move white rook (A1 to D1)
            Piece longRook = pMap.get(WR_longInitPos);
            pMap.put(WR_longCastlingMoveTarget, longRook);
            pMap.remove(WR_longInitPos);
        }
        else if (from == BK_initPos && to == BK_shortCastlingMoveTarget && pieceToMoveIsBlackKing) {
            // Black performing short castling(right side), move black rook (H8 to F8)
            Piece shortRook = pMap.get(BR_shortInitPos);
            pMap.put(BR_shortCastlingMoveTarget, shortRook);
            pMap.remove(BR_shortInitPos);
        }
        else if (from == BK_initPos && to == BK_longCastlingMoveTarget && pieceToMoveIsBlackKing) {
            // Black performing long castling(left side), move black rook (A8 to D8)
            Piece longRook = pMap.get(BR_longInitPos);
            pMap.put(BR_longCastlingMoveTarget, longRook);
            pMap.remove(BR_longInitPos);
        }

        // ouch, another special case..
        // update state of piece if it was moved, to notify MoveRuleStrategies of this (castling)
        if (pieceToMove instanceof StatePieceImpl) {
            ((StatePieceImpl)pieceToMove).setHasMoved(true);
        }

        // special case
        // replace pawn by queen if it reaches enemy players back row
        if (pieceToMove.getType().equals(GameConstants.PAWN)) {
            if (pieceToMove.getColor().equals(Color.WHITE)) {
                if (to == BoardPosition.A8 || to == BoardPosition.B8 ||
                        to == BoardPosition.C8 || to == BoardPosition.D8 ||
                        to == BoardPosition.E8 || to == BoardPosition.F8 ||
                        to == BoardPosition.G8 || to == BoardPosition.H8) {
                    Piece newWhiteQueen = new PieceImpl(new WhiteQueenPieceFactory());
                    pMap.put(to, newWhiteQueen);
                }
            } else {
                if (to == BoardPosition.A1 || to == BoardPosition.B1 ||
                        to == BoardPosition.C1 || to == BoardPosition.D1 ||
                        to == BoardPosition.E1 || to == BoardPosition.F1 ||
                        to == BoardPosition.G1 || to == BoardPosition.H1) {
                    Piece newBlackQueen = new PieceImpl(new BlackQueenPieceFactory());
                    pMap.put(to, newBlackQueen);
                }
            }
        }
    }

    private void swapPlayerTurn() {
        if (_playerInTurn == Color.BLACK) {
            _playerInTurn = Color.WHITE;
        } else {
            _playerInTurn = Color.BLACK;
        }
    }

    @Override
    public void addObserver(Observer o) {
        _observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        _observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : _observers) {
            o.update();
        }
    }
}
