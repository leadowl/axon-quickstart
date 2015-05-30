package eu.leadowl.tp.axon.quickstart.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class CreateToDoItemCommand {

	@TargetAggregateIdentifier
	private final String todoId;
	private final String description;

	public CreateToDoItemCommand(final String todoId, final String description) {
		this.todoId = todoId;
		this.description = description;
	}

	public String getTodoId() {
		return todoId;
	}

	public String getDescription() {
		return description;
	}
}