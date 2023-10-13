package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


import org.junit.jupiter.api.Test;
import org.testfx.framework.junit.ApplicationTest;

import javafx.scene.Scene;
import javafx.stage.Stage;
import seedu.address.logic.Logic;
import seedu.address.logic.commands.AddCommand;
import seedu.address.testutil.Assert;
import seedu.address.testutil.LogicStub;

public class MainWindowTest extends ApplicationTest {

    private MainWindow mainWindow;
    private Logic logic;


    @Override
    public void start(Stage stage) {
        logic = new LogicStub();
        mainWindow = new MainWindow(stage, logic);
        mainWindow.fillInnerParts();
        Scene scene = mainWindow.getRoot().getScene();
        stage.setScene(scene);
        stage.show();

    }


    @Test
    public void testFillInnerParts() {
        Assert.assertThrows(NullPointerException.class, () -> mainWindow.fillInnerParts());
        // verifyThat("#personListPanel", isVisible());

        // verifyThat("#appointmentListPanelPlaceholder #personListPanel", isVisible());

        // verifyThat("#statusbarPlaceholder #statusBarFooter", isVisible());

        // verifyThat("#commandBoxPlaceholder #commandBox", isVisible());
    }
}
