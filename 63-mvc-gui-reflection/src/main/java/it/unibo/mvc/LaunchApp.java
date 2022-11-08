package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;
import it.unibo.mvc.view.DrawNumberSwingView;
import it.unibo.mvc.view.DrawNumberTerminalView;

/**
 * Application entry-point.
 */
public final class LaunchApp {

    private static String DRAW_TERMINAL = "it.unibo.mvc.view.DrawNumberTerminalView";
    private static String DRAW_SWING = "it.unibo.mvc.view.DrawNumberSwingView";
    private static int CONSTRUCT_AMOUNT = 3;

    private LaunchApp() { }

    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws SecurityException
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    public static void main(final String... args) throws Exception {
        final var model = new DrawNumberImpl();
        final DrawNumberController app = new DrawNumberControllerImpl(model);
        
        /*
        app.addView(new DrawNumberTerminalView());
        app.addView(new DrawNumberSwingView());
        */

        reflectAddViews(app, DRAW_TERMINAL);
        reflectAddViews(app, DRAW_SWING);
    }

    private static void reflectAddViews(DrawNumberController app, String className) throws Exception {
        var constructor = Class.forName(className).getConstructor();
        for (int i=0; i<CONSTRUCT_AMOUNT; i++) {
            if (className.equals(DRAW_TERMINAL)) {
                app.addView((DrawNumberTerminalView) constructor.newInstance());
            } else {
                app.addView((DrawNumberSwingView) constructor.newInstance());
            }
        }
    }
}
