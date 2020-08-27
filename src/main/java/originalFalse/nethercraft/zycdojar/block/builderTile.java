package originalFalse.nethercraft.zycdojar.block;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import originalFalse.nethercraft.zycdojar.gui.containter;
import originalFalse.nethercraft.zycdojar.main;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Set;


public class builderTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public Inventory inventory=new Inventory(2);
    public static TileEntityType<?> type=
            TileEntityType.Builder.create(()->{return new builderTile();}, main.builder).build(null).setRegistryName(main.builder.getRegistryName());
    public builderTile() {
        super(type);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("锻造台");
    }

    @Override
    public void read(CompoundNBT compound) {
        ticker=compound.getInt("ticker");
        inventory.setInventorySlotContents(0,ItemStack.read(compound.getCompound("first")));
        inventory.setInventorySlotContents(1,ItemStack.read(compound.getCompound("second")));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("ticker", IntNBT.valueOf(ticker));
        compound.put("first",inventory.getStackInSlot(0).copy().serializeNBT());
        compound.put("second",inventory.getStackInSlot(1).copy().serializeNBT());
        return super.write(compound);
    }

    @Nullable
    @Override
    public Container createMenu(int p_createMenu_1_, PlayerInventory p_createMenu_2_, PlayerEntity p_createMenu_3_) {
        return new containter(p_createMenu_1_,p_createMenu_2_,pos,world);
    }
    public int ticker=0;
    @Override
    public void tick() {
        if(!world.isRemote){
            if(inventory.getStackInSlot(1).getItem().equals(originalFalse.tech.zycdojar.main.voidMeterial)){
                if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_SWORD)){
                    inventory.setInventorySlotContents(0,new ItemStack(main.sword));
                    inventory.setInventorySlotContents(1,ItemStack.EMPTY);
                    markDirty();
                }else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_SHOVEL)){
                    inventory.setInventorySlotContents(0,new ItemStack(main.shovel));
                    inventory.setInventorySlotContents(1,ItemStack.EMPTY);
                    markDirty();
                }else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_HOE)){
                    inventory.setInventorySlotContents(0,new ItemStack(main.hoe));
                    inventory.setInventorySlotContents(1,ItemStack.EMPTY);
                    markDirty();
                }else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_AXE)){
                    inventory.setInventorySlotContents(0,new ItemStack(main.axe));
                    inventory.setInventorySlotContents(1,ItemStack.EMPTY);
                    markDirty();
                }else if(inventory.getStackInSlot(0).getItem().equals(Items.DIAMOND_PICKAXE)){
                    inventory.setInventorySlotContents(0,new ItemStack(main.pickaxe));
                    inventory.setInventorySlotContents(1,ItemStack.EMPTY);
                    markDirty();
                }
            }
        }
    }

}
