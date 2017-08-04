typedef struct _Queue Queue;

Queue *queue_init(int size);

void queue_free(Queue *queue);

int queue_get_next(Queue *queue, int current);

void *queue_push(Queue *queue);

void *queue_pop(Queue *queue);

