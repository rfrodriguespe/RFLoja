package br.com.rfloja.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Rodrigo
 */
public class PcTableModel extends AbstractTableModel {

    private ArrayList<Pc> dados = new ArrayList<>();
    private String[] colunas = {"Cod", "Modelo", "Velocidade Ghz", "Tamanho Ram", "Tamanho HD","Preço" ,"Cód Fabricante"};

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
                return dados.get(linha).getVelocidadeGhz();
            case 3:
                return dados.get(linha).getTamanhoRAM();
            case 4:
                return dados.get(linha).getTamanhoHD();
            case 5:
                return dados.get(linha).getPreco();
            case 6:
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
                dados.get(linha).setVelocidadeGhz((float) valor);
                break;
            case 3:
                dados.get(linha).setTamanhoRAM((int) valor);
                break;
            case 4:
                dados.get(linha).setTamanhoHD((int) valor);
                break;
            case 5:
                dados.get(linha).setCodFabricante((int) valor);
                break;
        }
        this.fireTableRowsUpdated(linha, linha);
    }

    public void addRow(Pc pc) {
        this.dados.add(pc);
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
