package org.codekart.designDataStructure;

class SpreadSheet {

    private int rows;
    private int columns;
    private int[][] cells;

    public SpreadSheet(int rows) {
        this.rows = rows;
        this.columns = 26;
        this.cells = new int[rows][columns];
    }

    public void setCell(String cell, int value) throws Exception {
        if (cell.length() != 2) {
            throw new Exception("Invalid cell reference");
        }
        int row = Integer.parseInt(cell.substring(1)) - 1;
        int column = cell.charAt(0) - 'A';
        cells[row][column] = value;
    }

    public void resetCell(String cell) throws Exception {
        if (cell.length() != 2) {
            throw new Exception("Invalid cell reference");
        }
        int row = Integer.parseInt(cell.substring(1)) - 1;
        int column = cell.charAt(0) - 'A';
        cells[row][column] = 0;
    }

    public int getValue(String formula) throws Exception {
        if (formula.length() != 2) {
            throw new Exception("Invalid formula");
        }
        String formulaWithoutEqual = formula.substring(1);
        String[] tokens = formulaWithoutEqual.split("\\+");

        int XValue = evaluateToken(tokens[0]);
        int YValue = evaluateToken(tokens[1]);

        return XValue + YValue;
    }

    private int evaluateToken(String token) throws Exception {
        if (token.length() != 2) {
            throw new Exception("Invalid token");
        }
        if (Character.isLetter(token.charAt(0))) {
            // It's a cell reference
            int column = token.charAt(0) - 'A';
            int row = Integer.parseInt(token.substring(1)) - 1;
            return cells[row][column];
        } else {
            // It's a number
            return Integer.parseInt(token);
        }
    }

    public static void main(String[] args) {
        SpreadSheet spreadSheet = new SpreadSheet(3);
        try {
            spreadSheet.setCell("N3", 41);
            spreadSheet.setCell("F1", 29);
            System.out.println(spreadSheet.getValue("=N3+9026"));
            System.out.println(spreadSheet.getValue("=F1+N3"));
            spreadSheet.resetCell("F1");
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
