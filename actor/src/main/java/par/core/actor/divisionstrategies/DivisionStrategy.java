package par.core.actor.divisionstrategies;

import java.util.List;

import par.core.actor.annotations.Immutable;
import par.core.actor.base.ActorMessage;
import par.core.actor.base.node.Actor;

/**
 * A base division strategy template<br>
 * 
 * we choose a divisionStrategy that's exist in
 * {@code par.core.actor.divisionstrategies} or create a new one by
 * implementing this interface. <br>
 * <br>
 * whenever we send a message to an actor, before enquing operation we check if
 * any division condition is valid. if so we execute the related strategy.<br>
 * 
 * The strategy is build on how we want out actor behave to it's load<br>
 * it might create another child actor to pass some messages in some
 * circumstance. <br>
 * 
 * ex : <br>
 * 
 * <ul>
 * <li>if current queue size > X number then executeStrategy.</li>
 * <li>there's no strategy at all, the node is going to process all the data
 * that's sent to it</li>
 * <li>custom strategies.</li>
 * </ul>
 * 
 * @author osman.yasal
 *
 * @param <T> : actor message type.
 */
@Immutable
public interface DivisionStrategy<T> {

	/**
	 * before adding a message to the queue, checks the division rule. if the rule
	 * is satisfied then execute the strategy that's defined below.
	 * 
	 * @param actor
	 * @return
	 */
	boolean isConditionValid(Actor<T> actor);

	/**
	 * This strategy is for sending data one actor to other, these types of messages uses locking
	 * mechanism.
	 * 
	 * @param actor
	 * @param message
	 */
	void executeSendingStrategy(Actor<T> actor, List<ActorMessage<T>> message);

	/**
	 * This strategy is for sending messages to the parent or it's child actor, these types of messages DON'T use
	 * locking mechanism.
	 * 
	 * @param actor
	 * @param message
	 */
	void executeLoadingStrategy(Actor<T> actor, List<ActorMessage<T>> message);

}
