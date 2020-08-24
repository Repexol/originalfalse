package originalFalse.tech.zycdojar.client.block;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.client.model.data.EmptyModelData;
import originalFalse.tech.zycdojar.block.tile.mawoderendoushidashabi;

public class wuzhongshenyouyishi extends TileEntityRenderer<mawoderendoushidashabi> {
    public wuzhongshenyouyishi(TileEntityRendererDispatcher rendererDispatcherIn) {
        super(rendererDispatcherIn);
    }

    @Override
    public void render(mawoderendoushidashabi tileEntityIn, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn) {
        matrixStackIn.push();
        matrixStackIn.translate(0,3,0);
        BlockRendererDispatcher blockRenderer= Minecraft.getInstance().getBlockRendererDispatcher();
        BlockState state=Blocks.BEACON.getDefaultState();
        if(tileEntityIn.check())
        blockRenderer.renderBlock(state,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn,EmptyModelData.INSTANCE);
        matrixStackIn.pop();
        if(!tileEntityIn.check()){
            if(Minecraft.getInstance().player.getHeldItem(Hand.MAIN_HAND).getItem().equals(Items.STICK)) {
                int[][] struct = {{3, 0, 0}, {-3, 0, 0}, {0, 0, -3}, {0, 0, 3}, {2, 0, 2}, {2, 0, -2}, {-2, 0, 2}, {-2, 0, -2}, {0, -1, 0}, {-1, -1, -1}, {1, -1, 1}, {-1, -1, 1}, {1, -1, -1}, {0, -1, -1}, {-1, -1, 0}
                        , {0, -1, 1}, {1, -1, 0}};
                for (int[] str : struct) {
                    BlockPos pos = new BlockPos(str[0], str[1], str[2]);
                    if (!tileEntityIn.getWorld().getBlockState(tileEntityIn.getPos().add(str[0], str[1], str[2])).getBlock().equals(Blocks.DIAMOND_ORE))
                        rend(pos, Blocks.DIAMOND_ORE.getDefaultState(), matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn);
                }
            }
        }
        /*blockRenderer.renderItem(stack,
                matrixStackIn,
                bufferIn,
                combinedLightIn,
                combinedOverlayIn,
                EmptyModelData.INSTANCE);
         */
        matrixStackIn.push();
        matrixStackIn.translate(0.5,0.8,0.5);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        ItemStack stack=ItemStack.read(tileEntityIn.getTileData().getCompound("item"));
        IBakedModel ibakedmodel = itemRenderer.getItemModelWithOverrides(stack, tileEntityIn.getWorld(), null);
        itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStackIn, bufferIn, combinedLightIn, combinedOverlayIn, ibakedmodel);
        matrixStackIn.pop();
    }

    public void rend(BlockPos pos, BlockState state, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int combinedLightIn, int combinedOverlayIn){
        matrixStackIn.push();
        matrixStackIn.translate(pos.getX(),pos.getY(),pos.getZ());
        BlockRendererDispatcher blockRenderer= Minecraft.getInstance().getBlockRendererDispatcher();
        blockRenderer.renderBlock(state,matrixStackIn,bufferIn,combinedLightIn,combinedOverlayIn,EmptyModelData.INSTANCE);
        matrixStackIn.pop();
    }
}
