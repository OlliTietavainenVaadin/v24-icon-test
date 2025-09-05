package org.vaadin.base.ui;

import com.vaadin.flow.component.Unit;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.server.streams.DownloadHandler;
import com.vaadin.flow.server.streams.FileDownloadHandler;
import org.vaadin.base.ui.component.ViewToolbar;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Main;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.icon.SvgIcon;
import com.vaadin.flow.router.Menu;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.io.File;
import java.io.IOException;

/**
 * This view shows up when a user navigates to the root ('/') of the application.
 */
@Route
@Menu(order = -100, icon = "vaadin:home", title = "Welcome!")
public final class MainView extends Main {
    private final Image image;

    // TODO Replace with your own main view.

    MainView() {
        addClassNames(LumoUtility.Padding.MEDIUM, LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN,
                LumoUtility.BoxSizing.BORDER);
        setSizeFull();

        var contentDiv = new Div();
        contentDiv.addClassNames(LumoUtility.Flex.GROW, LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN,
                LumoUtility.AlignItems.CENTER, LumoUtility.JustifyContent.CENTER);


        var centerDiv = new Div();
        centerDiv.addClassNames(LumoUtility.Display.FLEX, LumoUtility.FlexDirection.COLUMN,
                LumoUtility.AlignItems.CENTER);

        image = new Image();
        image.setWidth(300, Unit.PIXELS);
        image.setHeight(450, Unit.PIXELS);
        File file = new File(".\\src\\main\\resources\\META-INF\\resources\\images\\test.png");
        FileDownloadHandler fileDownloadHandler = DownloadHandler.forFile(file);
        image.setSrc(fileDownloadHandler);
        image.addClickListener(e -> refreshImage());
        centerDiv.add(image);
        contentDiv.add(centerDiv);

        add(new ViewToolbar("Welcome to Vaadin!"));
        add(contentDiv);
    }

    private void refreshImage() {
        File file = new File(".\\src\\main\\resources\\META-INF\\resources\\images\\test.png");
        FileDownloadHandler fileDownloadHandler = DownloadHandler.forFile(file);
        image.setSrc(fileDownloadHandler);;
        try {
            Notification.show("Showing " + file.getCanonicalPath());
        } catch (IOException io) {
            Notification.show("IOException: " + io.getMessage());
        }

    }
}
