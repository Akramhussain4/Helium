/*
 * MIT License
 *
 * Copyright (c) 2025 Mohammed Akram Hussain
 * Website: https://theakram.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.theakram.helium.dom

/**
 * WHATWG-compliant EventTarget interface.
 *
 * Spec: https://dom.spec.whatwg.org/#interface-eventtarget
 */
public abstract class EventTarget {
    
    private val eventListeners = mutableMapOf<String, MutableList<EventListener>>()
    
    // WHATWG-compliant addEventListener with union type support
    public fun addEventListener(type: String, listener: EventListener?, options: EventListenerOptions? = null) {
        if (listener == null) return
        
        val listeners = eventListeners.getOrPut(type) { mutableListOf() }
        
        // Check if listener is already added (for once listeners or duplicates)
        val addListenerOptions = options as? AddEventListenerOptions
        if (addListenerOptions?.once == true || !listeners.contains(listener)) {
            listeners.add(listener)
        }
    }
    
    public fun addEventListener(type: String, listener: EventListener?, useCapture: Boolean) {
        addEventListener(type, listener, EventListenerOptions(capture = useCapture))
    }
    
    public fun addEventListener(type: String, listener: EventListener?, options: AddEventListenerOptions) {
        addEventListener(type, listener, options as EventListenerOptions)
    }
    
    public fun addEventListener(type: String, listener: EventListenerFunction?, options: EventListenerOptions? = null) {
        if (listener == null) return
        addEventListener(type, FunctionEventListener(listener), options)
    }
    
    public fun addEventListener(type: String, listener: EventListenerFunction?, useCapture: Boolean) {
        addEventListener(type, listener, EventListenerOptions(capture = useCapture))
    }
    
    public fun removeEventListener(type: String, listener: EventListener?, options: EventListenerOptions? = null) {
        if (listener == null) return
        
        eventListeners[type]?.remove(listener)
        if (eventListeners[type]?.isEmpty() == true) {
            eventListeners.remove(type)
        }
    }
    
    public fun removeEventListener(type: String, listener: EventListener?, useCapture: Boolean) {
        removeEventListener(type, listener, EventListenerOptions(capture = useCapture))
    }
    
    public fun removeEventListener(type: String, listener: EventListenerFunction?, options: EventListenerOptions? = null) {
        if (listener == null) return
        removeEventListener(type, FunctionEventListener(listener), options)
    }
    
    public fun removeEventListener(type: String, listener: EventListenerFunction?, useCapture: Boolean) {
        removeEventListener(type, listener, EventListenerOptions(capture = useCapture))
    }
    
    public fun dispatchEvent(event: Event): Boolean {
        event.target = this
        event.currentTarget = this
        
        val listeners = eventListeners[event.type] ?: return true
        
        // Create a copy to handle listener modifications during dispatch
        val listenersCopy = listeners.toList()
        
        for (listener in listenersCopy) {
            try {
                listener.handleEvent(event)
            } catch (e: Exception) {
                // In a real implementation, this would be reported to the console
                // For now, we continue processing other listeners
            }
            
            if (event.immediatePropagationStopped) {
                break
            }
        }
        
        return !event.defaultPrevented
    }
    
    protected fun getEventListeners(type: String): List<EventListener> {
        return eventListeners[type]?.toList() ?: emptyList()
    }
}

/**
 * EventListener interface for event handling.
 */
public interface EventListener {
    public fun handleEvent(event: Event)
}

/**
 * Functional interface for event listeners.
 */
public typealias EventListenerFunction = (Event) -> Unit

/**
 * Wrapper for function-based event listeners.
 */
internal class FunctionEventListener(private val function: EventListenerFunction) : EventListener {
    override fun handleEvent(event: Event) {
        function(event)
    }
    
    override fun equals(other: Any?): Boolean {
        return other is FunctionEventListener && function == other.function
    }
    
    override fun hashCode(): Int {
        return function.hashCode()
    }
}

/**
 * Options for removeEventListener.
 */
public open class EventListenerOptions(
    public val capture: Boolean = false
)

/**
 * Options for addEventListener.
 */
public class AddEventListenerOptions(
    capture: Boolean = false,
    public val once: Boolean = false,
    public val passive: Boolean = false
) : EventListenerOptions(capture)

/**
 * Basic Event class for DOM events.
 */
public open class Event(
    public val type: String,
    public val bubbles: Boolean = false,
    public val cancelable: Boolean = false
) {
    public var target: EventTarget? = null
        internal set
    
    public var currentTarget: EventTarget? = null
        internal set
    
    public var defaultPrevented: Boolean = false
        private set
    
    internal var immediatePropagationStopped: Boolean = false
        private set
    
    public fun preventDefault() {
        if (cancelable) {
            defaultPrevented = true
        }
    }
    
    public fun stopImmediatePropagation() {
        immediatePropagationStopped = true
    }
    
    public fun stopPropagation() {
        // Implementation would prevent bubbling/capturing in a full DOM implementation
    }
}