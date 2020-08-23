package originalFalse.superTool.zycdojar.item.tile;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.nbt.StringNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import originalFalse.superTool.zycdojar.event.tileRegister;
import originalFalse.superTool.zycdojar.item.designChart;

import java.util.HashMap;
import java.util.Map;

public class AnvilTile extends TileEntity {
   private Map<String,Integer> items=new HashMap<>();
    public AnvilTile() {
        super(tileRegister.Anvil);
        items.putIfAbsent("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons", 0);
        items.putIfAbsent("iron", 0);
        items.putIfAbsent("RTX_please", 0);
        items.putIfAbsent("Author.Zycdojar(qq.3321019091)", 0);
    }

    public boolean put(Item item,int amount){
        if(item.equals(Items.IRON_INGOT)){
            items.put("iron",items.getOrDefault("iron",0)+amount);
            markDirty();
            return true;
        }else if(item.equals(Items.NETHER_STAR)){
            items.put("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons",items.getOrDefault("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons",0)+amount);
            markDirty();
            return true;
        }else if(item.equals(Items.DIAMOND)){
            items.put("RTX_please",items.getOrDefault("RTX_please",0)+amount);
            markDirty();
            return true;
        }else if(item.equals(Items.DIRT)){
            items.put("Author.Zycdojar(qq.3321019091)",items.getOrDefault("Author.Zycdojar(qq.3321019091)",0)+amount);
            markDirty();
            return true;
        }

        return false;
    }
    public Map<Item,Integer> getMap(){
        Map<Item,Integer> out =new HashMap<>();
        out.put(Items.IRON_INGOT,items.get("iron"));
        out.put(Items.NETHER_STAR,items.get("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons"));
        out.put(Items.DIAMOND,items.get("RTX_please"));
        out.put(Items.DIRT,items.get("Author.Zycdojar(qq.3321019091)"));
        return out;
    }
    public void clear(){
        items=new HashMap<>();
        items.putIfAbsent("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons", 0);
        items.putIfAbsent("iron", 0);
        items.putIfAbsent("RTX_please", 0);
        items.putIfAbsent("Author.Zycdojar(qq.3321019091)", 0);
        markDirty();
    }
    public boolean get(ItemStack design){
        Map<String,Integer> needItem=designChart.getNeed(design);
        if(needItem.get("iron").equals(items.get("iron")) &&
                needItem.get("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons").equals(
                        items.get("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons"))&&
                needItem.get("RTX_please").equals(items.get("RTX_please"))&&
                needItem.get("Author.Zycdojar(qq.3321019091)").equals(items.get("Author.Zycdojar(qq.3321019091)"))
        ) {
            return true;
        }else return false;
    }
    @Override
    public CompoundNBT write(CompoundNBT compound) {
        CompoundNBT nbtc=new CompoundNBT();
        for(String s:items.keySet()){
            nbtc.put(s, IntNBT.valueOf(items.get(s)));
        }
        compound.put("please_buy_me_a_nvidia_rtx_20xx@Shanghai_Pudong",nbtc);
        compound.put("AWA",StringNBT.valueOf("20200817"));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        CompoundNBT nbtc=compound.getCompound("please_buy_me_a_nvidia_rtx_20xx@Shanghai_Pudong");
        for(String x:nbtc.keySet()){
            items.put(x,nbtc.getInt(x));
        }
        items.putIfAbsent("yezi_and_tangyauN(Anran)_is_a_good_uploader,but_they_are_pigeons", 0);
        items.putIfAbsent("iron", 0);
        items.putIfAbsent("RTX_please", 0);
        items.putIfAbsent("Author.Zycdojar(qq.3321019091)", 0);
        super.read(compound);
    }
}
