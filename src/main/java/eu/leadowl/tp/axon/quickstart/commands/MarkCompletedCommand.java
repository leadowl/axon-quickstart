package eu.leadowl.tp.axon.quickstart.commands;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

public class MarkCompletedCommand {

	@TargetAggregateIdentifier
	private final String todoId;

	public MarkCompletedCommand(final String todoId) {
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}
}