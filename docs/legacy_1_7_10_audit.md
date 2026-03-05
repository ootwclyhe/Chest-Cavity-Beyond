# 1.7.10 兼容性审计报告

此报告由 `tools/audit_legacy_compat.py` 自动生成。

## NeoForge API (1.7.10 不存在)
- 命中数: **84**
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondClient.java:3` -> `import net.neoforged.api.distmarker.Dist;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondClient.java:4` -> `import net.neoforged.bus.api.IEventBus;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondClient.java:5` -> `import net.neoforged.fml.ModContainer;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondClient.java:6` -> `import net.neoforged.fml.common.Mod;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondClient.java:7` -> `import net.neoforged.neoforge.common.NeoForge;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondConfig.java:3` -> `import net.neoforged.fml.event.config.ModConfigEvent;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyondConfig.java:4` -> `import net.neoforged.neoforge.common.ModConfigSpec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:4` -> `import net.neoforged.bus.api.IEventBus;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:5` -> `import net.neoforged.fml.ModContainer;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:6` -> `import net.neoforged.fml.common.Mod;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:7` -> `import net.neoforged.fml.config.ModConfig;`
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:8` -> `import net.neoforged.neoforge.common.NeoForge;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/PacketManager.java:3` -> `import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/PacketManager.java:4` -> `import net.neoforged.neoforge.network.registration.PayloadRegistrar;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/ClientPacketHandler.java:8` -> `import net.neoforged.neoforge.network.handling.IPayloadContext;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/server/ServerPacketHandler.java:5` -> `import net.neoforged.neoforge.network.handling.IPayloadContext;`
- `src/main/java/net/zhaiji/chestcavitybeyond/api/task/GuardianLaserTask.java:4` -> `import net.neoforged.neoforge.network.PacketDistributor;`
- `src/main/java/net/zhaiji/chestcavitybeyond/api/capability/IOrgan.java:14` -> `import net.neoforged.neoforge.common.damagesource.DamageContainer;`
- `src/main/java/net/zhaiji/chestcavitybeyond/api/capability/Organ.java:15` -> `import net.neoforged.neoforge.common.damagesource.DamageContainer;`
- `src/main/java/net/zhaiji/chestcavitybeyond/api/function/AttackConsumer.java:5` -> `import net.neoforged.neoforge.common.damagesource.DamageContainer;`
- ... 其余 64 条省略

## Data Components (1.20+ 特性)
- 命中数: **12**
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:3` -> `import net.minecraft.core.component.DataComponents;`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1235` -> `DataComponents.POTION_CONTENTS,`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1247` -> `PotionContents potioncontents = stack.get(DataComponents.POTION_CONTENTS);`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1259` -> `.getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY)`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/ChestCavityUtil.java:5` -> `import net.minecraft.core.component.DataComponents;`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/ChestCavityUtil.java:158` -> `stack.set(DataComponents.POTION_CONTENTS, calculatePotionContents(effects));`
- `src/main/java/net/zhaiji/chestcavitybeyond/recipe/VenomGlandRecipe.java:5` -> `import net.minecraft.core.component.DataComponents;`
- `src/main/java/net/zhaiji/chestcavitybeyond/recipe/VenomGlandRecipe.java:32` -> `PotionContents potionContents = stack.get(DataComponents.POTION_CONTENTS);`
- `src/main/java/net/zhaiji/chestcavitybeyond/recipe/VenomGlandRecipe.java:50` -> `} else if (stack.has(DataComponents.POTION_CONTENTS)) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/recipe/VenomGlandRecipe.java:54` -> `return ChestCavityUtil.attachPotionContents(venomGland.copy(), potion.get(DataComponents.POTION_CONTENTS).getAllEffects());`
- `src/main/java/net/zhaiji/chestcavitybeyond/event/CommonEventHandler.java:3` -> `import net.minecraft.core.component.DataComponents;`
- `src/main/java/net/zhaiji/chestcavitybeyond/event/CommonEventHandler.java:274` -> `boolean isWaterPotion = source.getDirectEntity() instanceof ThrownPotion potion && potion.getItem().getOrDefault(DataComponents.POTION_CONTENTS, PotionContents.EMPTY).is(Potions.WATER);`

## Attachment System (NeoForge 专属)
- 命中数: **12**
- `src/main/java/net/zhaiji/chestcavitybeyond/ChestCavityBeyond.java:26` -> `InitAttachmentType.ATTACHMENT_TYPE.register(modEventBus);`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitAttachmentType.java:3` -> `import net.neoforged.neoforge.attachment.AttachmentType;`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitAttachmentType.java:11` -> `public class InitAttachmentType {`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitAttachmentType.java:12` -> `public static final DeferredRegister<AttachmentType<?>> ATTACHMENT_TYPE = DeferredRegister.create(NeoForgeRegistries.ATTACHMENT_TYPES, ChestCavityBeyond.MOD_ID);`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitAttachmentType.java:14` -> `public static final Supplier<AttachmentType<ChestCavityData>> CHEST_CAVITY = ATTACHMENT_TYPE.register(`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitAttachmentType.java:16` -> `() -> AttachmentType.serializable(ChestCavityData::new)`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/ChestCavityUtil.java:23` -> `import net.zhaiji.chestcavitybeyond.register.InitAttachmentType;`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/ChestCavityUtil.java:49` -> `return entity.getData(InitAttachmentType.CHEST_CAVITY);`
- `src/main/java/net/zhaiji/chestcavitybeyond/attachment/ChestCavityData.java:16` -> `import net.neoforged.neoforge.attachment.IAttachmentHolder;`
- `src/main/java/net/zhaiji/chestcavitybeyond/attachment/ChestCavityData.java:78` -> `public ChestCavityData(IAttachmentHolder attachmentHolder) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/event/CommonEventHandler.java:38` -> `import net.zhaiji.chestcavitybeyond.register.InitAttachmentType;`
- `src/main/java/net/zhaiji/chestcavitybeyond/event/CommonEventHandler.java:198` -> `ChestCavityData data = entity.getData(InitAttachmentType.CHEST_CAVITY);`

## Modern serialization codec
- 命中数: **12**
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/UnopenableChestCavityMessagePacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/UnopenableChestCavityMessagePacket.java:13` -> `public static final StreamCodec<ByteBuf, UnopenableChestCavityMessagePacket> STREAM_CODEC = StreamCodec.composite(`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/ChestOpenerMessagePacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/ChestOpenerMessagePacket.java:13` -> `public static final StreamCodec<ByteBuf, ChestOpenerMessagePacket> STREAM_CODEC = StreamCodec.composite(`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/AddGuardianLaserRenderTaskPacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/AddGuardianLaserRenderTaskPacket.java:14` -> `public static final StreamCodec<ByteBuf, AddGuardianLaserRenderTaskPacket> STREAM_CODEC = StreamCodec.composite(`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/SyncChestCavityDataPacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/packet/SyncChestCavityDataPacket.java:20` -> `public static final StreamCodec<RegistryFriendlyByteBuf, SyncChestCavityDataPacket> STREAM_CODEC = StreamCodec.of(`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/server/packet/UseSkillPacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/server/packet/UseSkillPacket.java:16` -> `public static final StreamCodec<ByteBuf, UseSkillPacket> STREAM_CODEC = StreamCodec.composite(`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/server/packet/SyncSelectedSlotPacket.java:5` -> `import net.minecraft.network.codec.StreamCodec;`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/server/packet/SyncSelectedSlotPacket.java:13` -> `public static final StreamCodec<ByteBuf, SyncSelectedSlotPacket> STREAM_CODEC = StreamCodec.composite(`

## Java 21 pattern matching / modern APIs
- 命中数: **53**
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/ClientPacketHandler.java:33` -> `level.getEntity(packet.attackerId()) instanceof LivingEntity attacker`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/ClientPacketHandler.java:34` -> `&& level.getEntity(packet.targetId()) instanceof LivingEntity target`
- `src/main/java/net/zhaiji/chestcavitybeyond/network/client/ClientPacketHandler.java:61` -> `if (minecraft.level instanceof Level level && level.getEntity(packet.entityId()) instanceof LivingEntity entity) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/entity/ThrownCobweb.java:63` -> `if (state.isAir() && getDefaultItem() instanceof BlockItem item) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/entity/ThrownCobweb.java:78` -> `if (level().getBlockState(pos).isAir() && getDefaultItem() instanceof BlockItem item) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:73` -> `if (task instanceof IRenderTask rendererTask) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:140` -> `if (Minecraft.getInstance().screen instanceof OrganSkillScreen screen) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:163` -> `if (minecraft.screen instanceof OrganSkillScreen screen) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:188` -> `if (minecraft.screen instanceof OrganSkillScreen && event.getName().equals(VanillaGuiLayers.CROSSHAIR)) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:201` -> `if (minecraft.screen instanceof OrganSkillScreen) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/client/event/ClientEventHandler.java:223` -> `if (player.isPassenger() && player.getVehicle() instanceof LivingEntity vehicle) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:948` -> `if (context.entity() instanceof Player player) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1172` -> `if (context.entity() instanceof Player player) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1204` -> `if (context.entity() instanceof Player player) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/register/InitItem.java:1468` -> `if (context.entity() instanceof Player player) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/OrganSkillUtil.java:56` -> `if (entity instanceof Player player && !player.isCreative()) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/OrganSkillUtil.java:69` -> `if (entity instanceof Player player && !player.isCreative()) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/OrganSkillUtil.java:230` -> `if (entity instanceof Player player) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/OrganSkillUtil.java:341` -> `if (hitResult instanceof EntityHitResult entityHitResult) {`
- `src/main/java/net/zhaiji/chestcavitybeyond/util/OrganSkillUtil.java:393` -> `if (level instanceof ServerLevel serverLevel) {`
- ... 其余 33 条省略

