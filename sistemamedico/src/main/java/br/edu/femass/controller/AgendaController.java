package br.edu.femass.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.femass.dao.AgendaDao;
import br.edu.femass.dao.Dao;
import br.edu.femass.dao.EspecialidadeDao;
import br.edu.femass.dao.MedicoDao;
import br.edu.femass.dao.PacienteDao;
import br.edu.femass.diversos.DiversosJavaFx;
import br.edu.femass.model.Agenda;
import br.edu.femass.model.Especialidade;
import br.edu.femass.model.Medico;
import br.edu.femass.model.Paciente;
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

public class AgendaController implements Initializable {

    @FXML
    private TextField TxtId;

    @FXML
    private ComboBox<Paciente> CmbPaciente;

    @FXML
    private ComboBox<Especialidade> CmbEspecialidade;

    @FXML
    private ComboBox<Medico> CmbMedico;

    @FXML
    private TextField TxtData;


    @FXML
    private ListView<Agenda> listaAgenda;

    private Dao<Paciente> pacienteDao = new PacienteDao();
    private Dao<Agenda> agendaDao = new AgendaDao();
    private Dao<Especialidade> especialidadeDao = new EspecialidadeDao();
    private Dao<Medico> medicoDao = new MedicoDao();   

    @FXML
    private void listaAgenda_keyPressed(KeyEvent event){
        exibirDados();
    }  
    
    @FXML
    private void listaAgenda_mouseClicked (MouseEvent event){
        exibirDados();    
    } 

    private void exibirDados(){
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda==null) return;

        TxtId.setText(agenda.getId().toString());
        CmbPaciente.getSelectionModel().select(agenda.getPaciente());
        CmbEspecialidade.getSelectionModel().select(agenda.getEspecialidade());
        CmbMedico.getSelectionModel().select(agenda.getMedico());
    }



    @FXML
    private void BtnExcluir_Click(ActionEvent event) {
        Agenda agenda = listaAgenda.getSelectionModel().getSelectedItem();
        if (agenda ==null) return;
       
        try{
        if (agendaDao.excluir(agenda)==false){
                DiversosJavaFx.exibirMensagem("Não excluiu");
        }
        
        limparTela();
        exibirAgendas();
        }catch(Exception ex){
            ex.printStackTrace();
        }    
    }

   @FXML
    private void BtnGravar_Click(ActionEvent event) {
        try {
        Agenda agenda = new Agenda (TxtData.getText(),
        CmbPaciente.getSelectionModel().getSelectedItem(), 
        CmbMedico.getSelectionModel().getSelectedItem());
            TxtId.setText(agenda.getId().toString());
            
             if (agendaDao.gravar(agenda)==false){
                DiversosJavaFx.exibirMensagem("Não gravou!");
                return;
             }

            // limparTela();
             exibirAgendas(); }
             catch (Exception ex){
                DiversosJavaFx.exibirMensagem("Erro");
             }      

    } 

    public void limparTela(){
        TxtData.setText("");          
        CmbPaciente.getSelectionModel().select(0);
        CmbMedico.getSelectionModel().select(0);
        CmbEspecialidade.getSelectionModel().select(0);


    }
    public void exibirAgendas(){
        try{
       ObservableList<Agenda> data = FXCollections.observableArrayList(
        agendaDao.buscarAtivos()
       );
       listaAgenda.setItems(data);
        }
     catch(Exception ex){
            ex.printStackTrace();
       }
    }

    public void exibirPacientes (){
        try{
            ObservableList<Paciente> data = FXCollections.observableArrayList(
             pacienteDao.buscarAtivos()
            );
            CmbPaciente.setItems(data);
             }
          catch(Exception ex){
                 ex.printStackTrace();
            }
    }

    /*public void exibirMedicos (){
        try{
            CmbEspecialidade.getSelectionModel().getSelectedItem();
            ObservableList<Medico> data = FXCollections.observableArrayList(
             medicoDao.buscarAtivosEsp()
            );
            CmbMedico.setItems(data);
             }
          catch(Exception ex){
                 ex.printStackTrace();
            }
    }*/

    public void exibirEspecialidade (){
        try{
            ObservableList<Especialidade> data = FXCollections.observableArrayList(
             especialidadeDao.buscarAtivos()
            );
            CmbEspecialidade.setItems(data);
             }
          catch(Exception ex){
                 ex.printStackTrace();
            }
    }

    public void exibirMedicos (){
        try{
            ObservableList<Medico> data = FXCollections.observableArrayList(
             medicoDao.buscarAtivos()
            );
            CmbMedico.setItems(data);
             }
          catch(Exception ex){
                 ex.printStackTrace();
            }
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
         exibirAgendas();
         exibirPacientes();
         exibirEspecialidade ();
         exibirMedicos ();}
    
}

