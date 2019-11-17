package br.com.rfloja.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class FabricanteTableModel extends AbstractTableModel {

    private ArrayList<Fabricante> dados = new ArrayList<>();
    private String[] colunas = {"Cod", "Fabricante", "Obs"};
    
    @Override
    public String getColumnName(int col) {
        return colunas[col];
    }

    @Override
    public int getRowCount() {
        return dados.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return dados.get(linha).getCod();
            case 1:
                return dados.get(linha).getFabricante();
            case 2:
                return dados.get(linha).getObs();
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        switch (coluna) {
            case 0:
                dados.get(linha).setCod((int) valor);
                break;
            case 1:
                dados.get(linha).setFabricante((String) valor);
                break;
            case 2:
                dados.get(linha).setObs((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addRow(Fabricante fabricante) {
        this.dados.add(fabricante);
        this.fireTableDataChanged();
    }

    public void removeRow(int linha) {
        this.dados.remove(linha);
        this.fireTableRowsDeleted(linha, linha);
    }

    public void limpaTabela() {
        dados.clear();
        fireTableDataChanged();
    }
}