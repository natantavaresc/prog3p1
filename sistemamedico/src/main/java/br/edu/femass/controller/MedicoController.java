package br.edu.femass.controller;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import com.fasterxml.jackson.databind.DatabindException;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

public class MedicoController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         exibirMedico();
         exibirEspecialidade();
    }

    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtCrm;

    @FXML
    private ListView<Especialidade> listaEspecialidade;

    @FXML
    private ComboBox<Especialidade> CmbEspecialidade;

    @FXML
    private ListView<Medico> listaMedico;
    private Dao<Medico> medicoDao = new MedicoDao();
    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    //private Medico medico;

    @FXML
    private void listaMedico_keyPressed(KeyEvent event){
        exibirDados();
    }  
    
    @FXML
    private void listaMedico_mouseClicked (MouseEvent event){
        exibirDados();    
    } 

    @FXML
    private void BtnAddEspecialidade_click (ActionEvent event) {
    try{
     Medico medico = listaMedico.getSelectionModel().getSelectedItem();
     Especialidade especialidade = CmbEspecialidade.getSelectionModel().getSelectedItem();
     medico.addEspecialidade(especialidade);
     medicoDao.gravar(medico);
     //if (medicoDao.gravar(medico)==false){
      // DiversosJavaFx.exibirMensagem("Não gravou!");
      //  return;}
       } 
     catch (IOException e) {
        DiversosJavaFx.exibirMensagem("Não adicionou especialidade");
        e.printStackTrace();}
        exibirDados(); 
    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
        Medico medico = new Medico (
            TxtNome.getText(),
            TxtCrm.getText()
            );
            
             if (medicoDao.gravar(medico)==false){
                DiversosJavaFx.exibirMensagem("Não gravou!");
                return;
             }

            // limparTela();
             exibirMedico();
             exibirEspecialidade(); }
             catch (Exception ex){
                DiversosJavaFx.exibirMensagem("CPF inválido");
             }      

    }
    
    private void exibirDados(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;
        ObservableList<Especialidade> especialidades = FXCollections.observableArrayList(medico.getEspecialidade());
            listaEspecialidade.setItems(especialidades);
            try{
                ObservableList<Especialidade> data = FXCollections.observableArrayList(
                 especialidadeDao.buscarAtivos()
                );
         
                CmbEspecialidade.setItems(data);
                 }
              catch(Exception e){
                     e.printStackTrace();
                }
        
        
        TxtNome.setText(medico.getNome());
        TxtCrm.setText(medico.getCrm());
        //CmbEspecialidade = new ComboBox<Especialidade>();
        //ObservableList<Especialidade> especialidade = null;
        //try {
        //    ObservableList<Especialidade>  especialidade = FXCollections.observableArrayList(new EspecialidadeDao().buscar());
       // } catch (DatabindException e) {
       //     e.printStackTrace();
       // }
        //

    }
    /* 
    private void exibirDados(){
        Medico medico = listaMedico.getSelectionModel().getSelectedItem();
        if (medico==null) return;
        Especialidade especialidade = CmbEspecialidade.getSelectionModel().getSelectedItem();
        if (especialidade==null) return;

        ObservableList<Especialidade> especialidades = FXCollections.observableArrayList(medico.getEspecialidade());
            listaEspecialidade.setItems(especialidades);

        TxtCrm.setText(medico.getCrm());
        TxtNome.setText(medico.getNome());
    }*/





    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Medico medico= listaMedico.getSelectionModel().getSelectedItem();
        if (medico ==null) return;
       
        try{
        if (medicoDao.excluir(medico)==false){
                DiversosJavaFx.exibirMensagem("Não excluiu");
        }
        
        //limparTela();
        exibirMedico();
        exibirEspecialidade();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

    }

    

    /*public void limparTela(){
                    
                     TxtEmail.setText("");
                     TxtEnd.setText("");
                     TxtFone.setText("");
                     TxtNome.setText("");
                     TxtCPF.setText("");
                     TxtId.setText("");
                     //CmbPlano.setSelectionModel(null);

    }*/

    public void exibirMedico(){
        
        try{
            ObservableList<Medico> data = FXCollections.observableArrayList(
             medicoDao.buscarAtivos()
            );
     
            listaMedico.setItems(data);
             }
          catch(Exception e){
                 e.printStackTrace();
            }
    }

    public void exibirEspecialidade(){
        
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(
             especialidadeDao.buscarAtivos()
            );
     
            CmbEspecialidade.setItems(data);
             }
          catch(Exception e){
                 e.printStackTrace();
            }
    }
    /* 
    public void exibirEspecialidade(){
        
        try{
            List<Especialidade> especialidades = especialidadeDao.buscarAtivos();
       ObservableList<Especialidade> data = FXCollections.observableArrayList(
        especialidades
       );
       CmbEspecialidade.setItems(data);
        }
     catch(Exception e){
            e.printStackTrace();
       }
    }
*/
      
}