package br.com.rfloja.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class TipoImpressoraTableModel extends AbstractTableModel {

    private ArrayList<TipoImpressora> dados = new ArrayList<>();
    private String[] colunas = {"Cod", "Tipo", "Obs"};
    
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
                return dados.get(linha).getTipo();
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
                dados.get(linha).setTipo((String) valor);
                break;
            case 2:
                dados.get(linha).setObs((String) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addRow(TipoImpressora tipoImpressora) {
        this.dados.add(tipoImpressora);
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