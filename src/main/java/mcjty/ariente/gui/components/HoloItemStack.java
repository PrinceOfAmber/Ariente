package mcjty.ariente.gui.components;

import mcjty.ariente.gui.HoloGuiRenderTools;
import net.minecraft.item.ItemStack;

public class HoloItemStack extends AbstractHoloComponent {

    private final ItemStack stack;

    public HoloItemStack(double x, double y, double w, double h, ItemStack stack) {
        super(x, y, w, h);
        this.stack = stack;
    }

    @Override
    public void render(double cursorX, double cursorY) {
        HoloGuiRenderTools.renderItem(x, y, stack);
    }
}
