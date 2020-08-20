package originalFalse.zycdojar.item.block;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import originalFalse.zycdojar.event.registyevent.itemregister;

public class TileEntityTypeRegistry {
    public static TileEntityType<?> gssbET=
            TileEntityType.Builder.create(()->{return new gssbTile();},itemregister.getSSB).build(null).setRegistryName(itemregister.getSSB.getRegistryName());
    public static TileEntityType<?> bsT=
            TileEntityType.Builder.create(()->{return new bsTile();},itemregister.bossSpawner).build(null).setRegistryName(itemregister.bossSpawner.getRegistryName());
}
