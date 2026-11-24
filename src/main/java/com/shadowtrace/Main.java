
package com.shadowtrace;

import java.util.Scanner;
import java.util.List;

import com.shadowtrace.service.UserService;
import com.shadowtrace.service.CaseService;
import com.shadowtrace.service.SuspectService;
import com.shadowtrace.service.ClueService;
import com.shadowtrace.service.SolutionService;

import com.shadowtrace.model.User;
import com.shadowtrace.model.Case;
import com.shadowtrace.model.Suspect;
import com.shadowtrace.model.Clue;
import com.shadowtrace.model.Solution;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserService userService = new UserService();
        CaseService caseService = new CaseService();
        SuspectService suspectService = new SuspectService();
        ClueService clueService = new ClueService();
        SolutionService solutionService = new SolutionService();

        // Keep logged-in user available throughout the application
        User loggedInUser = null;

        System.out.println();
        System.out.println("========================================");
        System.out.println("          WELCOME TO SHADOWTRACE");
        System.out.println("       THE TRUTH IS HIDDEN IN CLUES");
        System.out.println("========================================");

        while (true) {

            System.out.println("\n========== MAIN MENU ==========");
            System.out.println("1. Register as Detective");
            System.out.println("2. Login");
            System.out.println("3. Case Management");
            System.out.println("4. Exit");

            System.out.print("\nEnter your choice: ");

            int choice = sc.nextInt();

            switch (choice) {

                case 1: {

                    System.out.println("\n===== DETECTIVE REGISTRATION =====");

                    sc.nextLine();

                    System.out.print("Enter Username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String password = sc.nextLine();

                    String result = userService.register(
                            username, email, password
                    );

                    System.out.println("\n" + result);

                    break;
                }

                case 2: {

                    System.out.println("\n===== DETECTIVE LOGIN =====");

                    sc.nextLine();

                    System.out.print("Enter Email: ");
                    String loginEmail = sc.nextLine();

                    System.out.print("Enter Password: ");
                    String loginPassword = sc.nextLine();

                    loggedInUser = userService.login(
                            loginEmail, loginPassword
                    );

                    if (loggedInUser != null) {

                        System.out.println("\nLogin successful!");
                        System.out.println("Welcome, "
                                + loggedInUser.getUsername() + "!");

                    } else {

                        System.out.println("\nInvalid email or password!");
                    }

                    break;
                }

                case 3: {

                    int caseChoice;
                    do {
                        System.out.println("\n===== CASE MANAGEMENT =====");
                        System.out.println("1. Create New Case");
                        System.out.println("2. View All Cases");
                        System.out.println("3. View Case Details");
                        System.out.println("4. Update Case Status");
                        System.out.println("5. Suspect Management");
                        System.out.println("6. Clue Management");
                        System.out.println("7. Solution Management");
                        System.out.println("8. Back to Main Menu");

                        System.out.print("\nEnter your choice: ");

                        caseChoice = sc.nextInt();

                    switch (caseChoice) {

                        case 1: {

                            sc.nextLine();

                            System.out.println("\n===== CREATE NEW CASE =====");

                            System.out.print("Enter Case Title: ");
                            String title = sc.nextLine();

                            System.out.print("Enter Case Description: ");
                            String description = sc.nextLine();

                            System.out.print("Enter Difficulty (Easy/Medium/Hard): ");
                            String difficulty = sc.nextLine();

                            System.out.print("Enter Correct Answer: ");
                            String correctAnswer = sc.nextLine();

                            String caseResult = caseService.createCase(
                                    title, description, difficulty, correctAnswer
                            );

                            System.out.println("\n" + caseResult);

                            break;
                        }

                        case 2: {

                            System.out.println("\n===== ALL MYSTERY CASES =====");

                            List<Case> allCases = caseService.getAllCases();

                            if (allCases.isEmpty()) {

                                System.out.println("No mystery cases available!");

                            } else {

                                for (Case mysteryCase : allCases) {

                                    System.out.println("\nCase ID: "
                                            + mysteryCase.getCaseId());

                                    System.out.println("Title: "
                                            + mysteryCase.getTitle());

                                    System.out.println("Difficulty: "
                                            + mysteryCase.getDifficulty());

                                    System.out.println("Status: "
                                            + mysteryCase.getStatus());

                                    System.out.println("--------------------------------");
                                }
                            }

                            break;
                        }

                        case 3: {

                            System.out.println("\n===== VIEW CASE DETAILS =====");

                            System.out.print("Enter Case ID: ");
                            int caseId = sc.nextInt();

                            Case mysteryCase = caseService.getCaseById(caseId);

                            if (mysteryCase != null) {

                                System.out.println("\n===== CASE DETAILS =====");

                                System.out.println("Case ID: "
                                        + mysteryCase.getCaseId());

                                System.out.println("Title: "
                                        + mysteryCase.getTitle());

                                System.out.println("Description: "
                                        + mysteryCase.getDescription());

                                System.out.println("Difficulty: "
                                        + mysteryCase.getDifficulty());

                                System.out.println("Status: "
                                        + mysteryCase.getStatus());

                            } else {

                                System.out.println("\nCase not found!");
                            }

                            break;
                        }

                        case 4: {

                            sc.nextLine();

                            System.out.println("\n===== UPDATE CASE STATUS =====");

                            System.out.print("Enter Case ID: ");
                            int updateCaseId = sc.nextInt();

                            sc.nextLine();

                            System.out.println("\nAvailable Statuses:");
                            System.out.println("1. OPEN");
                            System.out.println("2. IN_PROGRESS");
                            System.out.println("3. SOLVED");

                            System.out.print("\nEnter New Status: ");
                            String newStatus = sc.nextLine();

                            String updateResult = caseService.updateCaseStatus(
                                    updateCaseId, newStatus
                            );

                            System.out.println("\n" + updateResult);

                            break;
                        }

                        case 5: {

                            System.out.println("\n===== SUSPECT MANAGEMENT =====");
                            System.out.println("1. Add New Suspect");
                            System.out.println("2. View Suspects by Case ID");
                            System.out.println("3. Back to Case Management");

                            System.out.print("\nEnter your choice: ");

                            int suspectChoice = sc.nextInt();

                            switch (suspectChoice) {

                                case 1: {

                                    sc.nextLine();

                                    System.out.println("\n===== ADD NEW SUSPECT =====");

                                    System.out.print("Enter Case ID: ");
                                    int suspectCaseId = sc.nextInt();

                                    sc.nextLine();

                                    System.out.print("Enter Suspect Name: ");
                                    String suspectName = sc.nextLine();

                                    System.out.print("Enter Suspect Age: ");
                                    int suspectAge = sc.nextInt();

                                    sc.nextLine();

                                    System.out.print("Enter Occupation: ");
                                    String suspectOccupation = sc.nextLine();

                                    System.out.print("Enter Description: ");
                                    String suspectDescription = sc.nextLine();

                                    String suspectResult = suspectService.addSuspect(
                                            suspectCaseId,
                                            suspectName,
                                            suspectAge,
                                            suspectOccupation,
                                            suspectDescription
                                    );

                                    System.out.println("\n" + suspectResult);

                                    break;
                                }

                                case 2: {

                                    System.out.println("\n===== VIEW SUSPECTS =====");

                                    System.out.print("Enter Case ID: ");
                                    int viewCaseId = sc.nextInt();

                                    List<Suspect> suspectList =
                                            suspectService.getSuspectsByCaseId(viewCaseId);

                                    if (suspectList.isEmpty()) {

                                        System.out.println(
                                                "No suspects found for this case!"
                                        );

                                    } else {

                                        for (Suspect suspect : suspectList) {

                                            System.out.println("\nSuspect ID: "
                                                    + suspect.getSuspectId());

                                            System.out.println("Name: "
                                                    + suspect.getName());

                                            System.out.println("Age: "
                                                    + suspect.getAge());

                                            System.out.println("Occupation: "
                                                    + suspect.getOccupation());

                                            System.out.println("Description: "
                                                    + suspect.getDescription());

                                            System.out.println("--------------------------------");
                                        }
                                    }

                                    break;
                                }

                                case 3:

                                    System.out.println(
                                            "Returning to Case Management..."
                                    );

                                    break;

                                default:

                                    System.out.println("Invalid suspect choice!");
                            }

                            break;
                        }

                        case 6: {

                            int clueChoice;

                            do {

                                System.out.println("\n===== CLUE MANAGEMENT =====");
                                System.out.println("1. Add New Clue");
                                System.out.println("2. View Clues by Case ID");
                                System.out.println("3. Back to Case Management");

                                System.out.print("\nEnter your choice: ");

                                clueChoice = sc.nextInt();

                                switch (clueChoice) {

                                    case 1: {

                                        sc.nextLine();

                                        System.out.println("\n===== ADD NEW CLUE =====");

                                        System.out.print("Enter Case ID: ");
                                        int clueCaseId = sc.nextInt();

                                        sc.nextLine();

                                        System.out.print("Enter Clue Title: ");
                                        String clueTitle = sc.nextLine();

                                        System.out.print("Enter Clue Description: ");
                                        String clueDescription = sc.nextLine();

                                        System.out.println("\nClue Visibility:");
                                        System.out.println("1. Visible Clue");
                                        System.out.println("2. Hidden Clue");

                                        System.out.print("Enter your choice: ");
                                        int hiddenChoice = sc.nextInt();

                                        boolean clueHidden;

                                        if (hiddenChoice == 1) {
                                            clueHidden = false;
                                        } else if (hiddenChoice == 2) {
                                            clueHidden = true;
                                        } else {
                                            System.out.println("Invalid visibility choice!");
                                            break;
                                        }

                                        String clueResult = clueService.addClue(
                                                clueCaseId,
                                                clueTitle,
                                                clueDescription,
                                                clueHidden
                                        );

                                        System.out.println("\n" + clueResult);

                                        break;
                                    }

                                    case 2: {

                                        System.out.println("\n===== VIEW CLUES =====");

                                        System.out.print("Enter Case ID: ");
                                        int viewClueCaseId = sc.nextInt();

                                        List<Clue> clueList =
                                                clueService.getCluesByCaseId(viewClueCaseId);

                                        if (clueList.isEmpty()) {

                                            System.out.println(
                                                    "No clues found for this case!"
                                            );

                                        } else {

                                            for (Clue clue : clueList) {

                                                System.out.println("\nClue ID: "
                                                        + clue.getClueId());

                                                System.out.println("Title: "
                                                        + clue.getClueTitle());

                                                System.out.println("Description: "
                                                        + clue.getClueDescription());

                                                System.out.println("Visibility: "
                                                        + (clue.isHidden() ? "Hidden" : "Visible"));

                                                System.out.println("--------------------------------");
                                            }
                                        }

                                        break;
                                    }

                                    case 3:

                                        System.out.println(
                                                "\nReturning to Case Management..."
                                        );

                                        break;

                                    default:

                                        System.out.println("Invalid clue choice!");
                                }

                            } while (clueChoice != 3);

                            break;
                        }

                        case 7: {

                            if (loggedInUser == null) {

                                System.out.println(
                                        "\nPlease login first to use Solution Management!"
                                );

                                break;
                            }

                            int solutionChoice;

                            do {

                                System.out.println("\n===== SOLUTION MANAGEMENT =====");
                                System.out.println("1. Submit Solution");
                                System.out.println("2. View Submitted Solutions");
                                System.out.println("3. Back to Case Management");

                                System.out.print("\nEnter your choice: ");

                                solutionChoice = sc.nextInt();

                                switch (solutionChoice) {

                                    case 1: {

                                        sc.nextLine();

                                        System.out.println("\n===== SUBMIT SOLUTION =====");

                                        System.out.print("Enter Case ID: ");
                                        int solutionCaseId = sc.nextInt();

                                        sc.nextLine();

                                        System.out.print("Enter Your Final Answer: ");
                                        String submittedAnswer = sc.nextLine();

                                        String solutionResult =
                                                solutionService.submitSolution(
                                                        solutionCaseId,
                                                        loggedInUser.getUserId(),
                                                        submittedAnswer
                                                );

                                        System.out.println("\n" + solutionResult);

                                        break;
                                    }

                                    case 2: {

                                        System.out.println("\n===== VIEW SUBMITTED SOLUTIONS =====");

                                        System.out.print("Enter Case ID: ");
                                        int viewSolutionCaseId = sc.nextInt();

                                        List<Solution> solutionList =
                                                solutionService.getSolutionsByCaseId(
                                                        viewSolutionCaseId
                                                );

                                        if (solutionList.isEmpty()) {

                                            System.out.println(
                                                    "No submitted solutions found!"
                                            );

                                        } else {

                                            for (Solution solution : solutionList) {

                                                System.out.println("\nSolution ID: "
                                                        + solution.getSolutionId());

                                                System.out.println("Detective User ID: "
                                                        + solution.getUserId());

                                                System.out.println("Submitted Answer: "
                                                        + solution.getSubmittedAnswer());

                                                System.out.println("Result: "
                                                        + (solution.isCorrect()
                                                        ? "Correct" : "Incorrect"));

                                                System.out.println("Submitted At: "
                                                        + solution.getSubmittedAt());

                                                System.out.println("--------------------------------");
                                            }
                                        }

                                        break;
                                    }

                                    case 3:

                                        System.out.println(
                                                "\nReturning to Case Management..."
                                        );

                                        break;

                                    default:

                                        System.out.println("Invalid solution choice!");
                                }

                            } while (solutionChoice != 3);

                            break;
                        }

                        case 8:

                            System.out.println("Returning to Main Menu...");

                            break;

                        default:

                            System.out.println("Invalid choice!");
                    }
                    } while (caseChoice != 8);

                    break;
                }

                case 4: {

                    System.out.println("\nThank you for using SHADOWTRACE!");
                    System.out.println("Exiting application...");
                    sc.close();
                    return;
                }
                default:
                System.out.println("\nInvalid choice! Try again.");
            }
        }
    }
}
