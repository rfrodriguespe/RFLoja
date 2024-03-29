package br.com.rfloja.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class ImpressoraTableModel extends AbstractTableModel {

    private ArrayList<Impressora> dados = new ArrayList<>();
    private String[] colunas = {"Cod", "Modelo", "Preço", "Cód Tipo", "Cód Fabricante"};

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
                return dados.get(linha).getModelo();
            case 2:
                return dados.get(linha).getPreco();
            case 3:
                return dados.get(linha).getCodTipo();
            case 4:
                return dados.get(linha).getCodFabricante();
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
                dados.get(linha).setModelo((String) valor);
                break;
            case 2:
                dados.get(linha).setPreco((int) valor);
                break;
            case 3:
                dados.get(linha).setCodTipo((int) valor);
                break;
            case 4:
                dados.get(linha).setCodFabricante((int) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addRow(Impressora impressora) {
        this.dados.add(impressora);
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
