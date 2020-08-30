package originalFalse.findTheBestSpell.zycdojar;

import net.minecraft.world.World;
import net.minecraft.world.dimension.Dimension;
import net.minecraft.world.dimension.DimensionType;
import net.minecraftforge.common.ModDimension;

import java.util.function.BiFunction;

public class ModDimensions extends ModDimension {
    public ModDimensions(){
        super();
        setRegistryName("zycdojarsdimensions");
    }
    @Override
    public BiFunction<World, DimensionType, ? extends Dimension> getFactory() {
        return ((world, type) -> {
            return new zycddjsdimension(world,type);
        });
    }
}
