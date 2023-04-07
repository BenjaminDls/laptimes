package fr.benjamindanlos.laptimes.Observer

interface Subject {
	var subscribed: ArrayList<Observer>// = ArrayList()

	fun getData(): ByteArray?
	fun subscribe(obj: Observer){
		subscribed.add(obj)
	}

	fun unsubscribe(obj: Observer){
		subscribed.remove(obj)
	}

	fun notif(){
		for (s: Observer in subscribed){
			s.update(this)
		}
	}
}
