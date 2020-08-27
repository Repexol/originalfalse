package originalFalse.nuclearCraft.block;

import com.alee.managers.animation.easing.Back;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.DoubleNBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.nbt.LongNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.Direction;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import originalFalse.common.debuger;
import originalFalse.nuclearCraft.gui.nuclearui;
import originalFalse.nuclearCraft.main;
import originalFalse.tech.zycdojar.api.wrapper.NESystem;
import originalFalse.tech.zycdojar.item.pearl;
import originalFalse.tech.zycdojar.item.voidMeterial;
import originalFalse.zycdojar.item.GWingIngot;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class nuclearTile extends TileEntity implements ITickableTileEntity, INamedContainerProvider {
    public double hot=1;
    public double realengry=0.0;
    public double stable=1.0;
    public int engry=0;
    public Inventory inventory=new Inventory(3);
    public static TileEntityType<nuclearTile> type=
            (TileEntityType<nuclearTile>) TileEntityType.Builder.create(()->{return new nuclearTile();}, main.nuclear).build(null).setRegistryName(main.nuclear.getRegistryName());
    public nuclearTile() {
        super(type);
    }

    @Override
    public ITextComponent getDisplayName() {
        return new StringTextComponent("nuclear");
    }

    @Nullable
    @Override
    public Container createMenu(int sycID, PlayerInventory inventory, PlayerEntity player) {
        return new nuclearui(sycID,inventory,pos,world);
    }
    public int ticker=0;
    private int ticker2=0;
    private int ticker3=0;
    private int check(){
        int flag=0;
        int[][] struct={{0,1,0},{0,-1,0},{1,0,0},{-1,0,0},{0,0,1},{0,0,-1}};
        for(int[] blockpos:struct){
            if(world.getBlockState(pos.add(blockpos[0],blockpos[1],blockpos[2])).getBlock().equals(main.fan)){
                flag+=1;
            }
        }
        return flag;
    }
    private boolean checkWarm(){
        boolean flag=false;
        int[][] struct={{0,1,0},{0,-1,0},{1,0,0},{-1,0,0},{0,0,1},{0,0,-1}};
        for(int[] blockpos:struct){
            if(world.getBlockState(pos.add(blockpos[0],blockpos[1],blockpos[2])).getBlock().equals(main.Warm)){
                flag=true;
            }
        }
        return flag;
    }
    @Override
    public void tick() {
        if (!world.isRemote) {
            int stablecount = inventory.getStackInSlot(2).getCount();
            int fuelcount = inventory.getStackInSlot(1).getCount();
            debuger debuger = new debuger();
            if (inventory.getStackInSlot(2).getItem().equals(Items.DIAMOND) && inventory.getStackInSlot(1).getItem() instanceof voidMeterial) {
                if (stablecount > 0 && fuelcount > 0) {
                    stable = stablecount - 0.4*((0.5 * hot) * (65 - fuelcount));
                    if (stable <= 0.12) {
                        PlayerEntity player = world.getClosestPlayer(pos.getX(), pos.getY(), pos.getZ(), 20, true);
                        if (player != null) {
                            player.addPotionEffect(new EffectInstance(Effects.WITHER, 10, 10));
                        }
                        if (stablecount >= 2) {
                            inventory.getStackInSlot(2).setCount(stablecount - 1);
                            hot -= (hot / 10);
                        }
                    } else {
                        if (inventory.getStackInSlot(0).getItem() instanceof pearl) {
                            if (!inventory.getStackInSlot(0).getTag().getString("owner").equals("")) {
                                //debuger.sendMessage(new StringTextComponent((0.5 * hot) * (65 - fuelcount) - stablecount + ""));
                                engry = (int) Math.max(0, (0.5 * hot) * (65 - fuelcount) - stablecount);
                                realengry = (0.5 * hot) * (65 - fuelcount) - stablecount;
                                if (engry > 0) {
                                    if (ticker >= 4000) {
                                        inventory.getStackInSlot(1).setCount(inventory.getStackInSlot(1).getCount() - 1);
                                        ticker = 0;
                                    } else {
                                        ticker++;
                                    }
                                }
                                if (ticker3 >= 10) {
                                    NESystem.grantNE(inventory.getStackInSlot(0).getTag().getString("owner"), engry);
                                    double rhot=engry/50.0;
                                    for(int i=1;i<=check();i++){
                                        rhot/=10.0;
                                    }if(!checkWarm())
                                    hot += rhot;
                                    ticker3=0;
                                }else {
                                    ticker3++;
                                }
                            }
                        }else if(inventory.getStackInSlot(0).getItem().equals(Items.COAL)){
                            hot+=1;
                            inventory.getStackInSlot(0).setCount(inventory.getStackInSlot(0).getCount()-1);
                        }else{
                            /*if(hot>1){
                                hot-=(hot/10.0);
                            }else if(hot<1){
                                hot+=0.001;
                            }*/
                        }
                    }
                }
                if (ticker2 >= 10) {
                    world.notifyBlockUpdate(pos, getBlockState(), getBlockState(), Constants.BlockFlags.BLOCK_UPDATE);
                    markDirty();
                    ticker2 = 0;
                } else {
                    ticker2++;
                }
            }
        }
    }
    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT compoundNBT = super.getUpdateTag();
        compoundNBT.put("hot",DoubleNBT.valueOf(hot));
        compoundNBT.put("engry", IntNBT.valueOf(engry));
        compoundNBT.put("re", DoubleNBT.valueOf(realengry));
        compoundNBT.put("stab",DoubleNBT.valueOf(stable));
        compoundNBT.put("ticker", IntNBT.valueOf(ticker));
        return compoundNBT;
    }

    @Override
    public void handleUpdateTag(CompoundNBT compound) {
        hot=compound.getLong("hot");
        engry=compound.getInt("engry");
        this.realengry=compound.getDouble("re");
        this.stable=compound.getDouble("stab");
        ticker=compound.getInt("ticker");
    }
    @Override
    public void read(CompoundNBT compound) {
        inventory.setInventorySlotContents(0,ItemStack.read(compound.getCompound("doYouLikeEatingOuLiGei")));
        inventory.setInventorySlotContents(1,ItemStack.read(compound.getCompound("fuel")));
        inventory.setInventorySlotContents(2,ItemStack.read(compound.getCompound("stabilizer")));
        hot=Math.max(1,compound.getLong("hot"));
        super.read(compound);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fuel",inventory.getStackInSlot(1).copy().serializeNBT());
        compound.put("stabilizer",inventory.getStackInSlot(2).copy().serializeNBT());
        compound.put("doYouLikeEatingOuLiGei",inventory.getStackInSlot(0).copy().serializeNBT());
        compound.put("hot", DoubleNBT.valueOf(hot));
        return super.write(compound);
    }
}
