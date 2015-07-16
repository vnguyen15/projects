#include <stdlib.h>
#include "queue.h"

void queue_init(queue_t * queue) {
	queue->size = 0;
	queue->head = NULL;
	queue->tail = NULL;
	pthread_mutex_init(&queue->mutex, NULL);
}

void queue_destroy(queue_t * queue) {
	while (queue->head != NULL) {
		node_t * toDie = queue->head;
		queue->head = queue->head->next;
		free(toDie);
	}
	pthread_mutex_destroy(&queue->mutex);
}

void queue_add(queue_t * queue, void * item) {
	pthread_mutex_lock(&queue->mutex);
	int i;
	if (queue->tail == NULL)
		queue->head = queue->tail = (node_t*)malloc(sizeof(node_t));
	else {
		queue->tail->next = (node_t*)malloc(sizeof(node_t));
		for (i = 0; i < 200000; i++);
		queue->tail = queue->tail->next;
	}
	queue->tail->data = item;
	for (i = 0; i < 200000; i++);
	queue->tail->next = NULL;
	queue->size++;
	pthread_mutex_unlock(&queue->mutex);
}

void * queue_remove(queue_t * queue) {
	pthread_mutex_lock(&queue->mutex);
	int i;
	if (queue->size == 0) {
		pthread_mutex_unlock(&queue->mutex);
		return NULL;
	}
	void * data = queue->head->data;
	node_t * toDie = queue->head;
	for (i = 0; i < 200000; i++);
	queue->head = queue->head->next;
	free(toDie);
	if (queue->head == NULL)
		queue->tail = NULL;
	queue->size--;
	pthread_mutex_unlock(&queue->mutex);
	return data;
}

int queue_isEmpty(queue_t * queue) {
	pthread_mutex_lock(&queue->mutex);
	int retval = queue->size == 0;
	pthread_mutex_unlock(&queue->mutex);
	return retval;
}













