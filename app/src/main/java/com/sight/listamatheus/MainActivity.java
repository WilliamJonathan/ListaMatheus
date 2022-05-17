package com.sight.listamatheus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<String> lista = new ArrayList<String>();

    private TextView txt_lista;
    private EditText edt_nome_ou_no;
    private EditText edt_no;
    private Button btn_acao;
    private RadioButton rdn_inserePrimeiro;
    private RadioButton rdn_insereDepois;
    private RadioButton rdn_insereUltimo;
    private RadioButton rdn_removePrimeiro;
    private RadioButton rdn_removeUltimo;
    private RadioButton rdn_remove;
    private RadioGroup grupo;
    private String valores = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_lista = findViewById(R.id.txt_nome);
        edt_nome_ou_no = findViewById(R.id.edt_valor);
        edt_no = findViewById(R.id.edt_no);
        btn_acao = findViewById(R.id.btn_acao);

        //liga botão as views
        rdn_inserePrimeiro = findViewById(R.id.inserePrimeiro);
        rdn_insereDepois = findViewById(R.id.insereDepois);
        rdn_insereUltimo = findViewById(R.id.insereUltimo);
        rdn_removePrimeiro = findViewById(R.id.removePrimeiro);
        rdn_removeUltimo = findViewById(R.id.removeUltimo);
        rdn_remove = findViewById(R.id.remove);

        grupo = findViewById(R.id.radioGroup);

        btn_acao.setOnClickListener(view -> {
            if (grupo.getCheckedRadioButtonId() != -1){
                valores = "";
                txt_lista.setText("");
                metodo(grupo.getCheckedRadioButtonId());
                //Toast.makeText(MainActivity.this, "ID: " + grupo.getCheckedRadioButtonId(), Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(MainActivity.this, "Selecione um metodo", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void metodo(int id){
        String nome = edt_nome_ou_no.getText().toString();
        String no = edt_no.getText().toString();

        switch (id){
            case R.id.inserePrimeiro:
                if (!nome.isEmpty()){
                    inserePrimeiro(lista, nome);
                }else{
                    txt_lista.setText("Digite um nome");
                }
                break;
            case R.id.insereDepois:
                if (!nome.isEmpty() && !no.isEmpty()){
                    int noo = Integer.parseInt(no);
                    insereDepois(lista, nome, noo);
                }else{
                    txt_lista.setText("Digite um nome e um nó");
                }
                break;
            case R.id.insereUltimo:
                if (!nome.isEmpty()){
                    insereUltimo(lista, nome);
                }else{
                    txt_lista.setText("Digite um nome");
                }
                break;
            case R.id.removePrimeiro:
                removePrimeiro(lista);
                break;
            case R.id.removeUltimo:
                removeUltimo(lista);
                break;
            case R.id.remove:
                if (!no.isEmpty()){
                    int noo = Integer.parseInt(no);
                    remove(lista, noo);
                }else{
                    txt_lista.setText("Digite um nó para remover");
                }
                break;
        }
    }

    ///verifica se a lista esta vazia
    private boolean verifica_lista(List<String> list){

        //se lista estiver vazia retorna falso
        if (list == null || list.isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    ///insere dados na primeira posição da lista
    private void inserePrimeiro(List<String> list, String nome){

        list.add(0, nome);
        //Log.d("VALOR_LISTA: ", list.toString());
        for (int i=0; i < list.size(); i++){
            valores += list.get(i) + "\n";
        }
        txt_lista.setText(valores);

    }

    ///insere depois de um nó especifico da lista
    private void insereDepois(List<String> list, String nome, int no){
        if (verifica_lista(list)){
            int tamanhoLista = list.size();
            if (tamanhoLista  <= no){
                //Log.d("VALOR_LISTA: ", "ESTE NÓ NÃO EXISTE, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
                txt_lista.setText("ESTE NÓ NÃO EXISTE, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            }else {
                list.add(no + 1, nome);
                //Log.d("VALOR_LISTA: ", list.toString());
                for (int i=0; i< list.size(); i++){
                    valores += list.get(i) + "\n";
                }
                txt_lista.setText(valores);
            }
        }else{
            //Log.d("VALOR_LISTA: ", "LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            txt_lista.setText("LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
        }
    }

    ///insere no final da lista
    private void insereUltimo(List<String> list, String nome){
        if (verifica_lista(list)){
            list.add(nome);
            //Log.d("VALOR_LISTA: ", list.toString());
            for (int i=0; i< list.size(); i++){
                valores += list.get(i) + "\n";
            }
            txt_lista.setText(valores);
        }else{
            //Log.d("VALOR_LISTA: ", "LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            txt_lista.setText("LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
        }
    }

    ///remove primeiro nó da lista
    private void removePrimeiro(List<String> list) {
        if (verifica_lista(list)){
            list.remove(0);
            //Log.d("VALOR_LISTA: ", list.toString());
            for (int i=0; i< list.size(); i++){
                valores += list.get(i) + "\n";
            }
            txt_lista.setText(valores);
        }else{
            //Log.d("VALOR_LISTA: ", "LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            txt_lista.setText("LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
        }
    }

    ///remove ultimo nó da lista
    private void removeUltimo(List<String> list) {
        if (verifica_lista(list)){
            int in = list.size();
            list.remove(in - 1);
            //Log.d("VALOR_LISTA: ", list.toString());
            for (int i=0; i< list.size(); i++){
                valores += list.get(i) + "\n";
            }
            txt_lista.setText(valores);
        }else{
            //Log.d("VALOR_LISTA: ", "LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            txt_lista.setText("LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
        }
    }

    ///remove qualquer no da lista
    private void remove(List<String> list, int no) {
        if (verifica_lista(list)){
            int tamanhoLista = list.size();
            if (tamanhoLista  <= no){
                //Log.d("VALOR_LISTA: ", "ESTE NÓ NÃO EXISTE, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
                txt_lista.setText("ESTE NÓ NÃO EXISTE, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            }else {
                list.remove(no);
                //Log.d("VALOR_LISTA: ", list.toString());
                for (int i=0; i< list.size(); i++){
                    valores += list.get(i) + "\n";
                }
                txt_lista.setText(valores);
            }
        }else{
            //Log.d("VALOR_LISTA: ", "LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
            txt_lista.setText("LISTA VAZIA, IMPOSSIVEL FAZER ESTA OPERAÇÃO");
        }
    }

}