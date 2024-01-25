package fr.doandgo.gestionrh.ihm;

import fr.doandgo.gestionrh.controller.CompanyController;
import fr.doandgo.gestionrh.controller.EmployeeController;
import fr.doandgo.gestionrh.controller.JobController;
import fr.doandgo.gestionrh.controller.UserController;
import fr.doandgo.gestionrh.dto.*;
import fr.doandgo.gestionrh.entities.User;
import fr.doandgo.gestionrh.exception.NotFoundOrValidException;
import fr.doandgo.gestionrh.utils.IHMUtilsGet;
import fr.doandgo.gestionrh.utils.IHMUtilsSet;
import fr.doandgo.gestionrh.utils.Stylized3LText;
import fr.doandgo.gestionrh.utils.Stylized4LText;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Scanner;

@Component
public class UserMenu {

    private final UserController userController;
    private final IHMUtilsGet ihmUtilsGet;
    private final IHMUtilsSet ihmUtilsSet;
    private final Stylized3LText stylized3LText;
    private final Stylized4LText stylized4LText;



    public void userMenu(Scanner scanner) {
        displayUserMenu();
        String chosenUserMenuItem = scanner.nextLine();
        Integer choice = Integer.parseInt(chosenUserMenuItem);
        userMainSwitch(choice, scanner);
    }

    private void userMainSwitch(Integer choice, Scanner scanner) {
        switch (choice) {
            case 1:
                //stylized3LText.listEmployees();
                listUsers();
                break;
            case 2:
                //stylized3LText.listEmployees();
                createUser(scanner);
                break;
            case 3:
                //stylized3LText.createEmployee();
                updateUser(scanner);
                break;
            case 4:
                //stylized3LText.updateEmployee();
                deleteUser(scanner);
                break;
        }
    }

    private List<UserDto> listUsers(){
        List<UserDto> users = userController.getAll();
        for (UserDto user : users) {
            System.out.println("Id: " + user.id() + ", firstname: " + user.firstname() + ", lastname: " + user.lastname());
        }
        return users;
    }


    public void createUser(Scanner scanner){
        UserDto userDto = ihmUtilsSet.createUserDto(scanner);
        userController.create(userDto);
    }

    private void updateUser(Scanner scanner){
        List<UserDto> userDtos = listUsers();
        System.out.println("--  Sélection de l'utilisateur  --");
        for (UserDto usrDto : userDtos) {
            System.out.println("Id: " + usrDto.id() + ", firstname: " + usrDto.firstname() + ", lastname: " + usrDto.lastname());
        }
        Integer userId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Modifier le salarié n° : "));
        Optional<UserDto> optionalUser =  userDtos.stream().filter(emp -> Objects.equals(emp.id(), userId)).findFirst();
        if(optionalUser.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            UserDto updateUserDto = ihmUtilsSet.updateUserDto(scanner, optionalUser.get());
            userController.update(updateUserDto);
        }
    }

    public void deleteUser(Scanner scanner){
        List<UserDto> userDtos = listUsers();
        System.out.println("--  Sélection de l'utilisateur  --");
        for (UserDto usrDto : userDtos) {
            System.out.println("Id: " + usrDto.id() + ", firstname: " + usrDto.firstname() + ", lastname: " + usrDto.lastname());
        }
        Integer userId = Integer.parseInt(ihmUtilsGet.getUserInput(scanner, "Supprimer le salarié n° : "));
        Optional<UserDto> optionalUserDto =  userDtos.stream().filter(usr -> Objects.equals(usr.id(), userId)).findFirst();
        if(optionalUserDto.isEmpty()){
            throw new NotFoundOrValidException(new MessageDto("Employee not found"));
        }else {
            userController.deleteById(optionalUserDto.get().id());
        }
    }

    private void displayUserMenu() {
        //stylized4LText.employee();
        System.out.println("");
        System.out.println("");
        System.out.println("1. Lister tous les utilisateurs");
        System.out.println("2. Ajouter un utilisateur");
        System.out.println("3. Modifier un utilisateur");
        System.out.println("4. Supprimer un utilisateur");
        System.out.println("");
        System.out.println("Autres touches : retour au menu principal");
        System.out.println("");
        System.out.print("Choix n° ");
    }

    public UserMenu(UserController userController, IHMUtilsGet ihmUtilsGet, IHMUtilsSet ihmUtilsSet, Stylized3LText stylized3LText, Stylized4LText stylized4LText) {
        this.userController = userController;
        this.ihmUtilsGet = ihmUtilsGet;
        this.ihmUtilsSet = ihmUtilsSet;
        this.stylized3LText = stylized3LText;
        this.stylized4LText = stylized4LText;
    }

}
