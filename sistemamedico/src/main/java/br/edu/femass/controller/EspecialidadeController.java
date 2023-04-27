package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class EspecialidadeController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         exibirEspecialidade();}


    @FXML
    private TextField TxtTipo;


    @FXML
    private ListView<Especialidade> listaEspecialidade;

    private EspecialidadeDao especialidadeDao = new EspecialidadeDao();
    

    @FXML
    private void listaEspecialidade_keyPressed(KeyEvent event){
        exibirDados();
    }  
    
    @FXML
    private void listaEspecialidade_mouseClicked (MouseEvent event){
        exibirDados();    
    } 

    private void exibirDados(){
        Especialidade especialidade = listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;
        TxtTipo.setText(especialidade.getTipo());
    }

    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Especialidade especialidade= listaEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade ==null) return;
       
        try{
        if (especialidadeDao.excluir(especialidade)==false){
                DiversosJavaFx.exibirMensagem("Não excluiu");
        }
        
        limparTela();
        exibirEspecialidade();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
        Especialidade especialidade = new Especialidade (
            TxtTipo.getText()
            );            
             if (especialidadeDao.gravar(especialidade)==false){
                DiversosJavaFx.exibirMensagem("Não gravou!");
                return;
             }

             limparTela();
             exibirEspecialidade(); }
             catch (Exception ex){
                DiversosJavaFx.exibirMensagem("Errouu");
             }      

    }

    public void limparTela(){
                     TxtTipo.setText("");

    }
    public void exibirEspecialidade(){
        try{
       ObservableList<Especialidade> data = FXCollections.observableArrayList(
        especialidadeDao.buscar()
       );
       listaEspecialidade.setItems(data);
        }
     catch(Exception ex){
            ex.printStackTrace();
       }

        

    }

    
}

