import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;

import java.io.IOException;

/**
 * @author Adnane Ezouhri
 * @author Rida Errachidi
 * @author Ben DeSollar
 */
public class SelectRegistrationTypeController {
    /**
     * create new SceneController object
     */
    SceneController SceneSelector = new SceneController();
    /**
     * individual radio button
     */
    @FXML
    private RadioButton rButton2;
    /**
     * organization radio button
     */
    @FXML
    private RadioButton rButton;
    /**
     * organization radio button
     */
    @FXML
    private RadioButton rButton1;
    /**
     * register button
     */
    @FXML
    private Button selectionRegisterButton;

    /**
     * switch pages switches to correct registration page
     *
     * @param event input event
     * @throws IOException includes IOException if exists
     */
    public void switchPages(ActionEvent event) throws IOException {
        if (rButton.isSelected() && selectionRegisterButton.isArmed()) {
            SceneSelector.switchToOrgRegister(event);
        } else if (rButton1.isSelected() && selectionRegisterButton.isArmed()) {
            SceneSelector.switchToGovRegister(event);
        } else if (rButton2.isSelected() && selectionRegisterButton.isArmed()) {
            SceneSelector.switchToIndividualRegister(event);
        }
    }

}
