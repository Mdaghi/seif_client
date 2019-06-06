package seifClient;

import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.seif.interfaces.EmployeServiceRemote;
import tn.esprit.seif.interfaces.EntrepriseServiceRemote;
import tn.esprit.seif.interfaces.TimesheetServiceRemote;
import tn.esprit.seif.persistence.Contract;
import tn.esprit.seif.persistence.Departement;
import tn.esprit.seif.persistence.Employe;
import tn.esprit.seif.persistence.Entreprise;
import tn.esprit.seif.persistence.Mission;
import tn.esprit.seif.persistence.MissionExterne;
import tn.esprit.seif.persistence.Role;

public class Start {

	public static void main(String[] args) {
		try {

			String jndiName = "seif-ear/seif-ejb/EntrepriseService!tn.esprit.seif.interfaces.EntrepriseServiceRemote";
			Context context = new InitialContext();
			EntrepriseServiceRemote entrepriseProxy = (EntrepriseServiceRemote) context.lookup(jndiName);

			jndiName = "seif-ear/seif-ejb/EmployeService!tn.esprit.seif.interfaces.EmployeServiceRemote";
			context = new InitialContext();
			EmployeServiceRemote employeProxy = (EmployeServiceRemote) context.lookup(jndiName);

			jndiName = "seif-ear/seif-ejb/TimesheetService!tn.esprit.seif.interfaces.TimesheetServiceRemote";
			context = new InitialContext();
			TimesheetServiceRemote timeSheetProxy = (TimesheetServiceRemote) context.lookup(jndiName);

			Entreprise entreprise = new Entreprise();
			entreprise.setName("SSII Consulting");
			entreprise.setRaisonSocial("Cite El ghazela");
			int idEntreprise = entrepriseProxy.ajouterEntreprise(entreprise);

			Employe employe = new Employe();
			employe.setNom("Kallel");
			employe.setPrenom("Khaled");
			employe.setActif(true);
			employe.setEmail("Khaled.kallel@consulting.tn");
			employe.setRole(Role.INGENIEUR);
			int kallelId = employeProxy.ajouterEmploye(employe);

			employe = new Employe();
			employe.setNom("zitouni");
			employe.setPrenom("mohamed");
			employe.setActif(true);
			employe.setEmail("mohamed.zitouni@consulting.tn");
			employe.setRole(Role.TECHNICIEN);
			int zitouniId = employeProxy.ajouterEmploye(employe);

			employe = new Employe();
			employe.setNom("ouali");
			employe.setPrenom("aymen");
			employe.setActif(true);
			employe.setEmail("aymen.ouali@consulting.tn");
			employe.setRole(Role.INGENIEUR);
			int oualiId = employeProxy.ajouterEmploye(employe);

			employe = new Employe();
			employe.setNom("bouzid");
			employe.setPrenom("bochra");
			employe.setActif(true);
			employe.setEmail("bochra.bouzid@consulting.tn");
			employe.setRole(Role.CHEF_DEPARTEMENT);
			int bouzidId = employeProxy.ajouterEmploye(employe);

			employe = new Employe();
			employe.setNom("arbi");
			employe.setPrenom("yosra");
			employe.setActif(true);
			employe.setEmail("yosra.arbi@consulting.tn");
			employe.setRole(Role.CHEF_DEPARTEMENT);
			int arbiId = employeProxy.ajouterEmploye(employe);

			Contract contrat = new Contract();
			contrat.setSalaire(4500.0);
			contrat.setTypeContrat("CDI");
			contrat.setDateDebut(new Date());
			int idContrat = employeProxy.ajouterContrat(contrat);
			employeProxy.affecterContratAEmploye(idContrat, kallelId);

			contrat = new Contract();
			contrat.setSalaire(3300.0);
			contrat.setTypeContrat("CDI");
			contrat.setDateDebut(new Date());
			idContrat = employeProxy.ajouterContrat(contrat);
			employeProxy.affecterContratAEmploye(idContrat, arbiId);

			contrat = new Contract();
			contrat.setSalaire(3200.0);
			contrat.setTypeContrat("CDI");
			contrat.setDateDebut(new Date());
			idContrat = employeProxy.ajouterContrat(contrat);
			employeProxy.affecterContratAEmploye(idContrat, zitouniId);

			contrat = new Contract();
			contrat.setSalaire(5000.0);
			contrat.setTypeContrat("CDI");
			contrat.setDateDebut(new Date());
			idContrat = employeProxy.ajouterContrat(contrat);
			employeProxy.affecterContratAEmploye(idContrat, oualiId);

			contrat = new Contract();
			contrat.setSalaire(3100.0);
			contrat.setTypeContrat("CDI");
			contrat.setDateDebut(new Date());
			idContrat = employeProxy.ajouterContrat(contrat);
			employeProxy.affecterContratAEmploye(idContrat, bouzidId);

			Departement dep1 = new Departement();
			dep1.setName("Telecom");
			int idDep1 = entrepriseProxy.ajouterDepartement(dep1);
			entrepriseProxy.affecterDepartementAEntreprise(idDep1, idEntreprise);

			Departement dep2 = new Departement();
			dep2.setName("RH");
			int idDep2 = entrepriseProxy.ajouterDepartement(dep2);
			entrepriseProxy.affecterDepartementAEntreprise(idDep2, idEntreprise);

			employeProxy.affecterEmployeADepartement(kallelId, idDep2);
			employeProxy.affecterEmployeADepartement(zitouniId, idDep2);
			employeProxy.affecterEmployeADepartement(bouzidId, idDep2);
			employeProxy.affecterEmployeADepartement(kallelId, idDep1);
			employeProxy.affecterEmployeADepartement(zitouniId, idDep1);
			employeProxy.affecterEmployeADepartement(oualiId, idDep1);
			employeProxy.affecterEmployeADepartement(arbiId, idDep1);

			MissionExterne missionExterne = new MissionExterne();
			missionExterne.setDescription("");
			missionExterne.setName("Mise en place de 4G pour l'entreprise ...");
			missionExterne.setEmailFacturation("fabrication@orange.tn");
			missionExterne.setTauxJournalierMoyen(650.0);
			int missionId1 = timeSheetProxy.ajouterMission(missionExterne);
			timeSheetProxy.affecterMissionADepartement(missionId1, idDep1);

			missionExterne = new MissionExterne();
			missionExterne.setDescription("");
			missionExterne.setName("Dev d'un nouveau outil de vente");
			missionExterne.setEmailFacturation("fabrication@orange.tn");
			missionExterne.setTauxJournalierMoyen(475.0);
			int missionId2 = timeSheetProxy.ajouterMission(missionExterne);
			timeSheetProxy.affecterMissionADepartement(missionId2, idDep1);

			Mission mission = new Mission();
			mission.setDescription("");
			mission.setName("Dev d'un nouveau outil de vente");
			int missionId = timeSheetProxy.ajouterMission(mission);
			timeSheetProxy.affecterMissionADepartement(missionId, idDep2);

			
			timeSheetProxy.ajouterTimesheet(missionId1, zitouniId, new Date(), new Date());
			timeSheetProxy.ajouterTimesheet(missionId2, kallelId, new Date(), new Date());
			timeSheetProxy.ajouterTimesheet(missionId1, oualiId, new Date(), new Date());
			timeSheetProxy.ajouterTimesheet(missionId, zitouniId, new Date(), new Date());
			timeSheetProxy.ajouterTimesheet(missionId, kallelId, new Date(), new Date());
			
			/***************************************************************/
			/**                                                           **/
			/**                          VALIDE                           **/
			/**                                                           **/
			/**                                                           **/
			/***************************************************************/
			

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

}
