#ifndef _QUEUE_H_
#define _QUEUE_H_

#include <pthread.h>

typedef struct node {
	void * data;
	struct node * next;
} node_t;

typedef struct {
	int size;
	node_t * head;
	node_t * tail;
	pthread_mutex_t mutex;
} queue_t;

void queue_init(queue_t * queue);
void queue_destroy(queue_t * queue);
void queue_add(queue_t * queue, void * item);
void * queue_remove(queue_t * queue);
int queue_isEmpty(queue_t * queue);

#endif
