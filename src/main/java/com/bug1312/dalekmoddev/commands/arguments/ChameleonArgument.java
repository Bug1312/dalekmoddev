package com.bug1312.dalekmoddev.commands.arguments;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.swdteam.common.init.DMTardisRegistry;
import com.swdteam.common.tardis.Tardis;

import net.minecraft.command.CommandSource;
import net.minecraft.command.ISuggestionProvider;
import net.minecraft.util.ResourceLocation;

public class ChameleonArgument implements ArgumentType<ResourceLocation>, Serializable {

	private static final long serialVersionUID = 7899063015209167811L;
	private static final Collection<String> EXAMPLES = Arrays.asList("dalekmod:tardis_capsule", "dalekmod:police_box", "dalekmod:pagoda");

	public static ChameleonArgument chameleon() { return new ChameleonArgument(); }

	public static ResourceLocation getResource(CommandContext<CommandSource> context, String input) {
		return context.getArgument(input, ResourceLocation.class);
	}

	@Override
	public ResourceLocation parse(StringReader reader) throws CommandSyntaxException {
		return ResourceLocation.read(reader);
	}

	@Override
	public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> context, SuggestionsBuilder builder) {
		List<String> suggestions = new ArrayList<>();
		
		for (Entry<ResourceLocation, Tardis> tardis : DMTardisRegistry.getRegistry().entrySet()) {
			suggestions.add(tardis.getKey().toString());
		}

		return ISuggestionProvider.suggest(suggestions, builder);
	}
	
	@Override
	public Collection<String> getExamples() {
		return EXAMPLES;
	}

}
