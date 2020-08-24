package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class dengjiajiaohuanyiTile extends TileEntity {
    public Set<ItemStack> stacks=new HashSet<>();
    public Map<Item,Integer> items=new HashMap<>();
    public static TileEntityType<dengjiajiaohuanyiTile> type=
            (TileEntityType<dengjiajiaohuanyiTile>) TileEntityType.Builder.create(()->{return new dengjiajiaohuanyiTile();}, main.dengjiajiaohuanyi).build(null).setRegistryName(main.dengjiajiaohuanyi.getRegistryName());
    public dengjiajiaohuanyiTile() {
        super(type);
    }
    public boolean check(PlayerEntity tip){
        boolean flag=true;
        int[][] struct={{2,1,0},{2,0,0},{-2,1,0},{-2,0,0},{0,1,-2},{0,0,-2},{0,0,2},{0,1,2}};
        for(int[] str:struct){
            BlockPos pos=this.pos.add(str[0],str[1],str[2]);
            if(!world.getBlockState(pos).getBlock().equals(itemregister.ManaCore)){
                if(tip!=null) {
                    tip.sendMessage(new StringTextComponent("error at " + pos.getX() + " " + pos.getY() + " " + pos.getZ()));
                    tip.attemptTeleport(pos.getX(), pos.getY(), pos.getZ(), true);
                }
                flag=false;
            }
        }
        return flag;
    }



    @Override
    public void read(CompoundNBT compound) {
        ListNBT nbt= (ListNBT) compound.get("bilibiliTheRepexolStudio");
        if(nbt!=null)
        for(INBT inbt:nbt){
            stacks.add(ItemStack.read((CompoundNBT) inbt));
        }
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        ListNBT nbt=new ListNBT();
        for(ItemStack stack:stacks){
            nbt.add(stack.serializeNBT());
        }
        compound.put("bilibiliTheRepexolStudio",nbt);
        return super.write(compound);
    }
}
