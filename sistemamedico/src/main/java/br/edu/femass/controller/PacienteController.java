package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.Dao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.dao.PlanoSaudeDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Paciente;
import br.edu.femass.model.PlanoSaude;
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

public class PacienteController implements Initializable {

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         exibirPacientes();
         exibirPlanoSaude();}


    @FXML
    private TextField TxtNome;

    @FXML
    private TextField TxtId;

    @FXML
    private TextField TxtCPF;

    @FXML
    private ComboBox<PlanoSaude> CmbPlano;

    @FXML
    private TextField TxtEnd;

    @FXML
    private TextField TxtEmail;

    @FXML
    private TextField TxtFone;

    @FXML
    private ListView<Paciente> listaPaciente;
    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<PlanoSaude> planoSaudeDao = new PlanoSaudeDao();

    @FXML
    private void listaPaciente_keyPressed(KeyEvent event){
        exibirDados();
    }  
    
    @FXML
    private void listaPaciente_mouseClicked (MouseEvent event){
        exibirDados();    
    } 

    private void exibirDados(){
        Paciente paciente = listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente==null) return;

        TxtEmail.setText(paciente.getEmail());
        TxtEnd.setText(paciente.getEndereco());
        TxtFone.setText(paciente.getTelefones().get(0));
        TxtNome.setText(paciente.getNome());
        TxtCPF.setText(paciente.getCpf());
        TxtId.setText(paciente.getId().toString());
        CmbPlano.getSelectionModel().select(paciente.getPlanoSaude());
    }



    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Paciente paciente= listaPaciente.getSelectionModel().getSelectedItem();
        if (paciente ==null) return;
       
        try{
        if (pacienteDao.excluir(paciente)==false){
                DiversosJavaFx.exibirMensagem("Não excluiu");
        }
        
        limparTela();
        exibirPacientes();
        }catch(Exception ex){
            ex.printStackTrace();
        }
        

    }

    @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
        Paciente paciente = new Paciente (
            TxtCPF.getText(),
            TxtNome.getText(),
            TxtFone.getText(),
            CmbPlano.getSelectionModel().getSelectedItem()
            );
            paciente.setEmail(TxtEmail.getText());
            paciente.setEndereco(TxtEnd.getText());
            TxtId.setText(paciente.getId().toString());
            
             if (pacienteDao.gravar(paciente)==false){
                DiversosJavaFx.exibirMensagem("Não gravou!");
                return;
             }

             limparTela();
             exibirPacientes(); }
             catch (Exception ex){
                DiversosJavaFx.exibirMensagem("CPF inválido");
             }      

    }

    public void limparTela(){
                    
                     TxtEmail.setText("");
                     TxtEnd.setText("");
                     TxtFone.setText("");
                     TxtNome.setText("");
                     TxtCPF.setText("");
                     TxtId.setText("");
                     CmbPlano.getSelectionModel().select(0);

    }


    public void exibirPacientes(){
        try{
       ObservableList<Paciente> data = FXCollections.observableArrayList(
        pacienteDao.buscarAtivos()
       );

       listaPaciente.setItems(data);
        }
     catch(Exception e){
            e.printStackTrace();
       }
    }
    


       public void exibirPlanoSaude() {
        try {
            ObservableList<PlanoSaude> data = FXCollections.observableArrayList(
                planoSaudeDao.buscar()
            );
            CmbPlano.setItems(data);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
       }
}
    

    


