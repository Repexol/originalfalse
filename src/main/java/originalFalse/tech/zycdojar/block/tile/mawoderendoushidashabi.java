package originalFalse.tech.zycdojar.block.tile;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.StringTextComponent;
import originalFalse.tech.zycdojar.main;
import originalFalse.zycdojar.event.registyevent.itemregister;

public class mawoderendoushidashabi extends TileEntity {
    public static TileEntityType<mawoderendoushidashabi> type=
            (TileEntityType<mawoderendoushidashabi>) TileEntityType.Builder.create(()->{return new mawoderendoushidashabi();}, main.wuzhongshenyouyishi).build(null).setRegistryName(main.wuzhongshenyouyishi.getRegistryName());

    public mawoderendoushidashabi() {
        super(type);
    }
    public boolean check(PlayerEntity entity){
        boolean flag=true;
        int[][] struct={{3,0,0},{-3,0,0},{0,0,-3},{0,0,3},{2,0,2},{2,0,-2},{-2,0,2},{-2,0,-2}/*,{0,-1,0},{-1,-1,-1},{1,-1,1},{-1,-1,1},{1,-1,-1},{0,-1,-1},{-1,-1,0}
        ,{0,-1,1},{1,-1,0}*/};
        for(int[] str:struct){
            BlockPos pos=this.pos.add(str[0],str[1],str[2]);
            if(!world.getBlockState(pos).getBlock().equals(Blocks.DIAMOND_ORE)){
                entity.attemptTeleport(pos.getX(),pos.getY(),pos.getZ(),true);
                entity.sendMessage(new StringTextComponent("error at "+pos.getX()+" "+pos.getY()+" "+pos.getZ()));
                flag=false;
            }
        }
        return flag;
    }
    public boolean check(){
        boolean flag=true;
        int[][] struct={{3,0,0},{-3,0,0},{0,0,-3},{0,0,3},{2,0,2},{2,0,-2},{-2,0,2},{-2,0,-2}};
        for(int[] str:struct){
            BlockPos pos=this.pos.add(str[0],str[1],str[2]);
            if(!world.getBlockState(pos).getBlock().equals(Blocks.DIAMOND_ORE)){
                flag=false;
            }
        }
        return flag;
    }
}
