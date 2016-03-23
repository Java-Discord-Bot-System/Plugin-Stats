package com.almightyalpaca.discord.bot.plugin.stats;

import com.almightyalpaca.discord.bot.system.command.AbstractCommand;
import com.almightyalpaca.discord.bot.system.command.annotation.Command;
import com.almightyalpaca.discord.bot.system.events.CommandEvent;
import com.almightyalpaca.discord.bot.system.exception.PluginLoadingException;
import com.almightyalpaca.discord.bot.system.exception.PluginUnloadingException;
import com.almightyalpaca.discord.bot.system.plugins.Plugin;
import com.almightyalpaca.discord.bot.system.plugins.PluginInfo;

import net.dv8tion.jda.MessageBuilder;
import net.dv8tion.jda.MessageBuilder.Formatting;
import net.dv8tion.jda.entities.Guild;

public class StatsPlugin extends Plugin {

	class StatsCommand extends AbstractCommand {

		public StatsCommand() {
			super("stats", "Only some stats...", "");
		}

		@Command(dm = true, guild = true, async = true)
		private void onCommand(final CommandEvent event) {
			final MessageBuilder builder = new MessageBuilder();

			final int users = event.getJDA().getUsers().size();
			final int servers = event.getJDA().getGuilds().size();
			int channels = 0;

			for (final Guild s : event.getJDA().getGuilds()) {
				channels += s.getTextChannels().size();
			}

			builder.appendString("I'm in ");
			builder.appendString("" + servers, Formatting.BOLD).appendString(" server with ");
			builder.appendString("" + channels, Formatting.BOLD).appendString(" channels and ");
			builder.appendString("" + users, Formatting.BOLD).appendString(" known users.");

//			builder.newLine().newLine();
//
//			MemoryMXBean bean = ManagementFactory.getMemoryMXBean();
//
//			long used = bean.getHeapMemoryUsage().getUsed() + bean.getNonHeapMemoryUsage().getUsed();
//			long max = bean.getHeapMemoryUsage().getMax() + bean.getNonHeapMemoryUsage().getMax();
//			long free = max - used;
//			long total = Runtime.getRuntime().maxMemory();
//
//			String memory = "";
//			memory += "Used:   " + toMB(used) + "MB\n";
//			memory += "Free:   " + toMB(free) + "MB\n";
//			memory += "Total:  " + toMB(total) + "MB\n";
//			memory += "Max:    " + toMB(max) + "MB\n";
//
//			builder.appendString("Memory Usage:", Formatting.BOLD).newLine().appendCodeBlock(memory, "");

			event.sendMessage(builder.build());

		}

	}

	private static final PluginInfo INFO = new PluginInfo("com.almightyalpaca.discord.bot.plugin.stats", "1.0.0", "Almighty Alpaca", "Stats Plugin", "Some stats...");

	public StatsPlugin() {
		super(StatsPlugin.INFO);
	}

	@Override
	public void load() throws PluginLoadingException {
		this.registerCommand(new StatsCommand());
	}

	public float toMB(final long b) {
		return (float) Math.round(b / 1024 / 1024 * 10) / 10;
	}

	@Override
	public void unload() throws PluginUnloadingException {}
}
