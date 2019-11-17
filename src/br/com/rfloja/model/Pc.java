/*
 * The MIT License
 *
 * Copyright 2019 Rodrigo Ferreira Rodrigues <https://github.com/rfrodriguespe>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package br.com.rfloja.model;

/**
 *
 * @author Rodrigo Ferreira Rodrigues <https://github.com/rfrodriguespe>
 */
public class Pc {
    
    private int cod;
    private String modelo;
    private float velocidadeGhz;
    private int tamanhoRAM;
    private int tamanhoHD;
    private int preco;
    private int codFabricante;

    public Pc() {
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public float getVelocidadeGhz() {
        return velocidadeGhz;
    }

    public void setVelocidadeGhz(float velocidadeGhz) {
        this.velocidadeGhz = velocidadeGhz;
    }

    public int getTamanhoRAM() {
        return tamanhoRAM;
    }

    public void setTamanhoRAM(int tamanhoRAM) {
        this.tamanhoRAM = tamanhoRAM;
    }

    public int getTamanhoHD() {
        return tamanhoHD;
    }

    public void setTamanhoHD(int tamanhoHD) {
        this.tamanhoHD = tamanhoHD;
    }

    public int getPreco() {
        return preco;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public int getCodFabricante() {
        return codFabricante;
    }

    public void setCodFabricante(int codFabricante) {
        this.codFabricante = codFabricante;
    }
    
    
    
    
}