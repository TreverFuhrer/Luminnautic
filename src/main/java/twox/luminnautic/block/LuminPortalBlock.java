package twox.luminnautic.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.util.RandomSource;

import twox.luminnautic.registry.ModBlocks;

public class LuminPortalBlock extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public LuminPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED)
                ? Fluids.WATER.getSource(false)
                : super.getFluidState(state);
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        ItemStack stack = player.getItemInHand(hand);
        if (stack.getItem() != Items.BRICK) {
            return InteractionResult.PASS;
        }

        if (tryActivatePortal(world, pos)) {
            return InteractionResult.sidedSuccess(world.isClientSide);
        }
        return InteractionResult.PASS;
    }

    @Override
    public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if(random.nextInt(5) == 0) {
            world.addParticle(ParticleTypes.END_ROD,
                pos.getX() + random.nextDouble(),
                pos.getY() + random.nextDouble(),
                pos.getZ() + random.nextDouble(),
                0, 0.05, 0);
        }
    }

    public static boolean tryActivatePortal(Level world, BlockPos activationPos) {
        BlockPos center = findPortalCenter(world, activationPos);
        if (center == null) {
            return false;
        }

        if (!world.isClientSide) {
            activatePortal(world, center);
        }
        return true;
    }

    private static BlockPos findPortalCenter(Level world, BlockPos activationPos) {
        for (int y = -1; y <= 1; y++) {
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    BlockPos candidateCenter = activationPos.offset(x, y, z);
                    if (isFrameValid(world, candidateCenter) && isFullyUnderwater(world, candidateCenter)) {
                        return candidateCenter;
                    }
                }
            }
        }
        return null;
    }

    private static boolean isFullyUnderwater(Level world, BlockPos center) {
        for (int x = -2; x <= 2; x++) {
            for (int z = -2; z <= 2; z++) {
                BlockPos portalArea = center.offset(x, 0, z);
                BlockPos abovePortalArea = portalArea.above();
                if (!isWater(world, portalArea) || !isWater(world, abovePortalArea)) {
                    return false;
                }
            }
        }
        return true;
    }

    private static boolean isWater(Level world, BlockPos pos) {
        return world.getFluidState(pos).is(FluidTags.WATER);
    }

    private static boolean isFrameValid(Level world, BlockPos center) {
        // End-portal style frame: 5x5 outer square, 3x3 inner portal, 12 frame blocks (corners ignored)
        int[] dx = {-2, -1, 0, 1, 2};
        int[] dz = {-2, -1, 0, 1, 2};

        for (int x : dx) {
            for (int z : dz) {
                BlockPos check = center.offset(x, 0, z);

                // Skip inner 3x3
                if (Math.abs(x) <= 1 && Math.abs(z) <= 1) {
                    continue;
                }

                // Skip corners
                if (Math.abs(x) == 2 && Math.abs(z) == 2) {
                    continue;
                }

                BlockState frameState = world.getBlockState(check);
                if (frameState.getBlock() != ModBlocks.LUMIN_PORTAL_BLOCK) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void activatePortal(Level world, BlockPos center) {
        for (int x = -1; x <= 1; x++) {
            for (int z = -1; z <= 1; z++) {
                BlockPos pos = center.offset(x, 0, z);
                BlockState portalState = ModBlocks.LUMIN_PORTAL.defaultBlockState();
                if (portalState.hasProperty(WATERLOGGED)) {
                    portalState = portalState.setValue(WATERLOGGED, world.getFluidState(pos).getType() == Fluids.WATER);
                }
                world.setBlock(pos, portalState, 3); // Replace inner 3x3 with portal block

                // Spawn some particles
                for (int i = 0; i < 5; i++) {
                    world.addParticle(ParticleTypes.END_ROD,
                        pos.getX() + world.random.nextDouble(),
                        pos.getY() + world.random.nextDouble(),
                        pos.getZ() + world.random.nextDouble(),
                        0, 0.05, 0);
                }
            }
        }
    }
}
