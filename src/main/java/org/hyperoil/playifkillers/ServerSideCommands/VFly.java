package org.hyperoil.playifkillers.ServerSideCommands;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.network.chat.ChatType;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.OutgoingChatMessage;
import net.minecraft.network.chat.PlayerChatMessage;
import net.minecraft.server.level.ServerPlayer;
import org.hyperoil.playifkillers.Utilities.CommandRegistrar;
import org.hyperoil.playifkillers.Utilities.VFlyEnabled;

public class VFly extends CommandRegistrar {
    public VFly(CommandDispatcher<CommandSourceStack> dispatcher) {
        super(dispatcher);
    }

    @Override
    protected int commandExecute(CommandContext<CommandSourceStack> context) {
        ServerPlayer sp = context.getSource().getPlayer();
        if (sp == null) return Command.SINGLE_SUCCESS;
        if (!sp.hasPermissions(2)) {
            sp.sendSystemMessage(Component.literal("Sorry You need to be at least operator level 2 to use vfly."));
            return Command.SINGLE_SUCCESS;
        }
        if (VFlyEnabled.getIsVFlyEnabled(sp)) {
            VFlyEnabled.setIsVFlyEnabled(sp, false);
        } else {
            VFlyEnabled.setIsVFlyEnabled(sp, true);
        }
        return Command.SINGLE_SUCCESS;
    }

    @Override
    protected String getCommandName() {
        return "vfly";
    }
}
