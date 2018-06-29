package mcjty.ariente.gui.components;

import mcjty.ariente.gui.IGuiComponent;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HoloPanel extends AbstractHoloComponent {

    private final List<IGuiComponent> children = new ArrayList<>();

    public HoloPanel(double x, double y, double w, double h) {
        super(x, y, w, h);
    }

    public HoloPanel add(IGuiComponent... components) {
        Collections.addAll(children, components);
        return this;
    }

    @Override
    public void render(double cursorX, double cursorY) {
        for (IGuiComponent child : children) {
            child.render(cursorX, cursorY);
        }
    }

    @Override
    public void hit(double cursorX, double cursorY) {
        for (IGuiComponent child : children) {
            if (child.isInside(cursorX, cursorY)) {
                child.hit(cursorX, cursorY);
            }
        }

    }
}
