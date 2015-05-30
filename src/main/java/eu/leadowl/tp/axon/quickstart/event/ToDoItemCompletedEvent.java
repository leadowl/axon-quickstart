package eu.leadowl.tp.axon.quickstart.event;

public class ToDoItemCompletedEvent {

	private final String todoId;

	public ToDoItemCompletedEvent(final String todoId) {
		this.todoId = todoId;
	}

	public String getTodoId() {
		return todoId;
	}
}
