package org.eu.awesomekalin.jta.mod.blocks.directional.street;

import org.eu.awesomekalin.jta.mod.blocks.DirectionalBlockExtension;
import org.mtr.mapping.holder.*;
import org.mtr.mapping.mapper.BlockHelper;
import org.mtr.mapping.mapper.PlayerHelper;
import org.mtr.mapping.mapper.TextHelper;

public class BritishTrashBin extends DirectionalBlockExtension {
    public BritishTrashBin() {
        super(BlockHelper.createBlockSettings(false).strength(4.0f).nonOpaque().dynamicBounds());
    }

    @Override
    public VoxelShape getOutlineShape2(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return BlockHelper.shapeUnion(Block.createCuboidShape(0, 0, 0, 16, 26, 16));
    }

    @Override
    public ActionResult onUse2(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack item = player.getMainHandStack();
        player.sendMessage(new Text(TextHelper.translatable("gui.jta.thrown_litter", new Object[]{item.getCount(), item.getItem().getName().data.getString()}).data), true);

        item.setCount(0);
        PlayerHelper.getPlayerInventory(player).setStack(PlayerHelper.getPlayerInventory(player).getSelectedSlotMapped(), item);
        return ActionResult.CONSUME;
    }
}
