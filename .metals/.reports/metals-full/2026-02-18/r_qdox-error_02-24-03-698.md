error id: file://<WORKSPACE>/src/main/java/twox/luminnautic/registry/ModBlocks.java
file://<WORKSPACE>/src/main/java/twox/luminnautic/registry/ModBlocks.java
### com.thoughtworks.qdox.parser.ParseException: syntax error @[53,2]

error in qdox parser
file content:
```java
offset: 1674
uri: file://<WORKSPACE>/src/main/java/twox/luminnautic/registry/ModBlocks.java
text:
```scala
package twox.luminnautic.registry;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CoralBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import twox.luminnautic.Luminnautic;

public final class ModBlocks {
	private static final int GLOWING_CORAL_LUMINANCE = 15;
	private static final Map<String, Block> REGISTERED_BLOCKS = new LinkedHashMap<>();

	public static final Block GLOWING_BRAIN_CORAL_BLOCK = registerCoralBlock(
		"glowing_brain_coral_block",
		Blocks.BRAIN_CORAL_BLOCK,
		Blocks.DEAD_BRAIN_CORAL_BLOCK
	);
	public static final Block GLOWING_BUBBLE_CORAL_BLOCK = registerCoralBlock(
		"glowing_bubble_coral_block",
		Blocks.BUBBLE_CORAL_BLOCK,
		Blocks.DEAD_BUBBLE_CORAL_BLOCK
	);
	public static final Block GLOWING_FIRE_CORAL_BLOCK = registerCoralBlock(
		"glowing_fire_coral_block",
		Blocks.FIRE_CORAL_BLOCK,
		Blocks.DEAD_FIRE_CORAL_BLOCK
	);
	public static final Block GLOWING_HORN_CORAL_BLOCK = registerCoralBlock(
		"glowing_horn_coral_block",
		Blocks.HORN_CORAL_BLOCK,
		Blocks.DEAD_HORN_CORAL_BLOCK
	);
	public static final Block GLOWING_TUBE_CORAL_BLOCK = registerCoralBlock(
		"glowing_tube_coral_block",
		Blocks.TUBE_CORAL_BLOCK,
		Blocks.DEAD_TUBE_CORAL_BLOCK
	);

	Public methods
	/*
	* Public methods
	*/
	p@@ublic static void register() {
		Luminnautic.LOGGER.info("Registered {} blocks", REGISTERED_BLOCKS.size());
	}

	public static List<Block> all() {
		return List.copyOf(REGISTERED_BLOCKS.values());
	}

	/*
	* Registers
	*/

	// Default register
	private static Block registerBlock(String name, Supplier<Block> blockFactory) {
		ResourceLocation id = new ResourceLocation(Luminnautic.MOD_ID, name);
		Block block = Registry.register(BuiltInRegistries.BLOCK, id, blockFactory.get());
		Registry.register(BuiltInRegistries.ITEM, id, new BlockItem(block, new Item.Properties()));
		REGISTERED_BLOCKS.put(name, block);
		return block;
	}

	// Custom registers

	// Coral
	private static Block registerCoralBlock(String name, Block liveVariant, Block deadVariant) {
		return registerBlock(name, () -> new CoralBlock(
			deadVariant,
			BlockBehaviour.Properties.copy(liveVariant).lightLevel(state -> GLOWING_CORAL_LUMINANCE)
		));
	}

}

```

```



#### Error stacktrace:

```
com.thoughtworks.qdox.parser.impl.Parser.yyerror(Parser.java:2025)
	com.thoughtworks.qdox.parser.impl.Parser.yyparse(Parser.java:2147)
	com.thoughtworks.qdox.parser.impl.Parser.parse(Parser.java:2006)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:232)
	com.thoughtworks.qdox.library.SourceLibrary.parse(SourceLibrary.java:190)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:94)
	com.thoughtworks.qdox.library.SourceLibrary.addSource(SourceLibrary.java:89)
	com.thoughtworks.qdox.library.SortedClassLibraryBuilder.addSource(SortedClassLibraryBuilder.java:162)
	com.thoughtworks.qdox.JavaProjectBuilder.addSource(JavaProjectBuilder.java:174)
	scala.meta.internal.mtags.JavaMtags.indexRoot(JavaMtags.scala:49)
	scala.meta.internal.metals.SemanticdbDefinition$.foreachWithReturnMtags(SemanticdbDefinition.scala:99)
	scala.meta.internal.metals.Indexer.indexSourceFile(Indexer.scala:560)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3(Indexer.scala:691)
	scala.meta.internal.metals.Indexer.$anonfun$reindexWorkspaceSources$3$adapted(Indexer.scala:688)
	scala.collection.IterableOnceOps.foreach(IterableOnce.scala:630)
	scala.collection.IterableOnceOps.foreach$(IterableOnce.scala:628)
	scala.collection.AbstractIterator.foreach(Iterator.scala:1313)
	scala.meta.internal.metals.Indexer.reindexWorkspaceSources(Indexer.scala:688)
	scala.meta.internal.metals.MetalsLspService.$anonfun$onChange$2(MetalsLspService.scala:936)
	scala.runtime.java8.JFunction0$mcV$sp.apply(JFunction0$mcV$sp.scala:18)
	scala.concurrent.Future$.$anonfun$apply$1(Future.scala:691)
	scala.concurrent.impl.Promise$Transformation.run(Promise.scala:500)
	java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1144)
	java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:642)
	java.base/java.lang.Thread.run(Thread.java:1570)
```
#### Short summary: 

QDox parse error in file://<WORKSPACE>/src/main/java/twox/luminnautic/registry/ModBlocks.java