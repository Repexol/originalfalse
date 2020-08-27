package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.main;

public class xukongjinshudabaifadianji extends TileEntity implements ITickableTileEntity {
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new xukongjinshudabaifadianji();}, main.xukongjinshudabaifadianji).build(null).setRegistryName(main.xukongjinshudabaifadianji.getRegistryName());
    public xukongjinshudabaifadianji() {
        super(type);
    }
    private int tickd=0;

    /**
     * 详情https://neutrino.v2mcdev.com/tileentity/itickabletileentity.html
     * 每tick发电
     */
    @Override
    public void tick() {
        CompoundNBT nbt=getTileData();
        //如果没有主人
        if(!nbt.getString("player").equals("")){
            if(tickd==50){
                if(NESystem.getNE(nbt.getString("player"))<1000)
                NESystem.grantNE(nbt.getString("player"),2);
                tickd=0;
            }else {
                tickd++;
            }
        }
    }
}
