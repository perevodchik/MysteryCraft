package perevodchik.village.text;

import net.minecraft.util.EnumFacing;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestVillageCreationHandler implements VillagerRegistry.IVillageCreationHandler {
    @Override
    public StructureVillagePieces.PieceWeight getVillagePieceWeight(Random random, int i) {
        return new StructureVillagePieces.PieceWeight((Class<? extends StructureVillagePieces.Village>) getComponentClass(), 5, 3);
    }

    @Override
    public Class<?> getComponentClass() {
        ArrayList<Class<?>> l = new ArrayList<>();
        l.add(TestVillageHouse.class);
        l.add(TestVillageHouse1.class);
        return l.get(new Random().nextInt(l.size()-1));
    }

    @Override
    public StructureVillagePieces.Village buildComponent(StructureVillagePieces.PieceWeight villagePiece, StructureVillagePieces.Start startPiece, List<StructureComponent> pieces, Random random, int parMinX, int parMinY, int parMinZ, EnumFacing facing, int type) {
        StructureBoundingBox structBB = StructureBoundingBox.getComponentToAddBoundingBox(parMinX, parMinY, parMinZ, 0, 0, 0, 9, 7, 12, facing);
        ArrayList<StructureVillagePieces.Village> a = new ArrayList<>();
        a.add(new TestVillageHouse(startPiece, type, random, structBB, facing));
        a.add(new TestVillageHouse1(startPiece, type, random, structBB, facing));
        return a.get(new Random().nextInt(a.size()-1));
    }
}
