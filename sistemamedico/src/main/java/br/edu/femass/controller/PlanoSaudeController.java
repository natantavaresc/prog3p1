package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.PlanoSaude;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class PlanoSaudeController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         exibirPlanos();}


    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtANS;


    @FXML
    private ListView<PlanoSaude> listaPlano;

    private PlanoSaudeDao planoSaudeDao = new PlanoSaudeDao();
    

    @FXML
    private void listaPlano_keyPressed(KeyEvent event){
        exibirDados();
    }  
    
    @FXML
    private void listaPlano_mouseClicked (MouseEvent event){
        exibirDados();    
    } 

    private void exibirDados(){
        PlanoSaude planoSaude = listaPlano.getSelectionModel().getSelectedItem();
        if (planoSaude==null) return;

        TxtANS.setText(planoSaude.getAns());
        TxtNome.setText(planoSaude.getNome());

    }



    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        PlanoSaude planoSaude= listaPlano.getSelectionModel().getSelectedItem();
        if (planoSaude ==null) return;
       
        try{
        if (planoSaudeDao.excluir(planoSaude)==false){
                DiversosJavaFx.exibirMensagem("Não excluiu");
        }
        
        limparTela();
        exibirPlanos();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
        PlanoSaude planoSaude = new PlanoSaude (
            TxtNome.getText(),
            TxtANS.getText()
            );            
             if (planoSaudeDao.gravar(planoSaude)==false){
                DiversosJavaFx.exibirMensagem("Não gravou!");
                return;
             }

             limparTela();
             exibirPlanos(); }
             catch (Exception ex){
                DiversosJavaFx.exibirMensagem("Errouu");
             }      

    }

    public void limparTela(){
                     TxtNome.setText("");
                     TxtANS.setText("");

    }
    public void exibirPlanos(){
        try{
       ObservableList<PlanoSaude> data = FXCollections.observableArrayList(
        planoSaudeDao.buscar()
       );
       listaPlano.setItems(data);
        }
     catch(Exception ex){
            ex.printStackTrace();
       }

        

    }

    
}

