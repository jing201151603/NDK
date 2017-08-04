
#include <malloc.h>
#include "queue.h"

struct _Queue {

    /**
     * 队列的长度
     */
    int size;

    /**
     * 二级指针主要用于指针数组
     */
    void **tab;

    //push或pop元素时需要按照先后顺序，依次进行
    int next_to_write;
    int next_to_read;

    int *ready;

};

/**
 * 初始化队列
 * @param size
 * @return
 */
Queue *queue_init(int size) {
    Queue *queue = malloc(sizeof(Queue));
    queue->size = size;
    queue->next_to_write = 0;
    queue->next_to_read = 0;

    //数组开辟空间
    queue->tab = malloc(sizeof(*queue->tab) * size);
    int i;
    for (i = 0; i < size; i++) {
        queue->tab[i] = malloc(sizeof(*queue->tab));
    }

    return queue;
}

/**
 * 释放队列内存
 */
void queue_free(Queue *queue) {
    int i;
    for (i = 0; i < queue->size; ++i) {
        free(queue->tab[i]);
    }
    free(queue->tab);
    free(queue);
}

int queue_get_next(Queue *queue, int current) {
    return (current + 1) % queue->size;
}

/**
 * 队列压入元素
 * @param queue
 * @return
 */
void *queue_push(Queue *queue) {

    int current = queue->next_to_write;
    queue->next_to_write = queue_get_next(queue, current);
    return queue->tab[current];
}

/**
 * 弹出元素
 * @param queue
 * @return
 */
void *queue_pop(Queue *queue) {
    int current = queue->next_to_read;
    queue->next_to_read = queue_get_next(queue, current);
    return queue->tab[current];
}

