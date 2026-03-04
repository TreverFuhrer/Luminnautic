package twox.luminnautic.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.util.RandomSource;

public class LuminPortal extends Block implements SimpleWaterloggedBlock {

    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public LuminPortal(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, false));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(WATERLOGGED);
    }

    @Override
    public BlockState getStateForPlacement(net.minecraft.world.item.context.BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        return this.defaultBlockState().setValue(WATERLOGGED, fluid.getType() == Fluids.WATER);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

@Override
public void animateTick(BlockState state, Level world, BlockPos pos, RandomSource random) {
        if(random.nextInt(3) == 0) { // more particles for shimmer
            world.addParticle(ParticleTypes.END_ROD,
                pos.getX() + random.nextDouble(),
                pos.getY() + random.nextDouble(),
                pos.getZ() + random.nextDouble(),
                0, 0.07, 0
            );
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return true; // makes it partially transparent
    }

    public boolean isSuffocating(BlockState state, BlockGetter reader, BlockPos pos) {
        return false; // non-solid
    }

    @Override
    public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter reader, BlockPos pos) {
        return false; // non-solid
    }
}