/*Viet Nguyen
  TCSS 422
  HW 2 
  Fall 2014
  =================== Webcrawler in C with Multi_Threaded ======================*/

#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <assert.h>
#include <string.h>
#include "util.h"
#include "queue.h"
#include <time.h>

/* The number of max pages are allowed to search */
#define MAX_PAGE 200
int totalNumberOfWebPages = 0;
int totalNumberOfURLs = 0;
char* processdUrls[200]; // initializing list of page visit
int size = 0; // size of processdurls
int isGetContentThreadProcessing = 0; // set to false
int isScanForURLsThreadProcessing = 0; // set to false
// main page / start page
char rootUrl[] = "http://css.insttech.washington.edu/~mealden/";

// initializing storage queues for managing url and contents
queue_t UrlQueue;
queue_t ContentQueue;
queue_t Folder;

// struct contain urlfolder and content of web page
typedef struct CurrentURLFolderAndItsContent {
	char* URLFolder;
	char* content;
} CurrentURLFolderAndItsContentType;

// functions pass in threads
void*  GetContentThreadMethod(void* args);
void* ScanForURLsThreadMethod(void* args);

int main(int argc, char* argv[]) {

	clock_t start = clock(), diff; // start timer

	// initializing storage for urls and contents
	queue_init(&UrlQueue);
	queue_init(&ContentQueue);
	queue_init(&Folder);

	// add root, 1st page to url queue
	queue_add(&UrlQueue, (void*) rootUrl);

	printf("================================================================\n");

	pthread_t T1, T2;

	/* create 1st thread */
	if(pthread_create(&T1, NULL,  GetContentThreadMethod, "T1") ) {

		fprintf(stderr, "Error creating thread\n");
		return 1;
	}

	 //create 2nd thread
	if(pthread_create(&T2, NULL, ScanForURLsThreadMethod, "T2")) {

		fprintf(stderr, "Error creating thread\n");
		return 1;
	}

	pthread_join(T1, NULL);
	pthread_join(T2, NULL);

	printf("================================================================\n");
	printf("Total number of web pages: %i\n" , totalNumberOfWebPages);
	printf("Average number of embedded URLs per page: %f\n" , (double)totalNumberOfURLs / totalNumberOfWebPages);

	diff = clock() - start;
	int msec = diff * 1000 / CLOCKS_PER_SEC;
	int average = msec / 13;
	printf("Average analysis time per page: %d seconds %d milliseconds \n", average/1000, average%1000);
	//printf("Average analysis time per page: " + (double)(totalTime.TotalSeconds / totalNumberOfWebPages));
	printf("Total running time: %d seconds %d milliseconds \n", msec/1000, msec%1000);
	printf("================================================================\n");
	return EXIT_SUCCESS;
}

// this thread get content from url, processing url
void* GetContentThreadMethod(void* args) {
	setvbuf(stdout, NULL, _IONBF, 0);
	char buffer[80], * content;

	 // Pick URL from the UrlQueue.
	 // Get content of the URL and put to content queue.
	do {
		char* nextUrl;
		isGetContentThreadProcessing = 1;
		int isEmpty = queue_isEmpty(&UrlQueue);
		if (isEmpty == 0) {
			nextUrl = queue_remove(& UrlQueue);

			int i = 0;
			int found = -1;
			// check if this nextUrl is already visited
			while(i < size) {
				found = strcmp(processdUrls[i], nextUrl);
				if (found == 0)
				{

					break; // break out if already visited
				}
				i++;
			}
			// if nextUrl is not visited yet then do
			if (found != 0) {
				processdUrls[size] = nextUrl;
				size++;

				// get content of page
				if (getMIMEType(nextUrl, buffer, 80)) {
					if (strstr(buffer, "text/html"))
					{
						totalNumberOfWebPages++;
						printf("Page retrieved (%i): %s\n", totalNumberOfWebPages, nextUrl);
						content = getContent(nextUrl);

						char* urlfolder = malloc(2000);
						memset(urlfolder, 0, 2000);

						// get the last 5 characters of url
						int length = ((int)strlen(nextUrl)) - 5;
						char endHtml[5];
						int j;
						for (j = 0; j < 5; j++) {
							endHtml[j]= nextUrl[length + j];
						}
						int result = strcmp(endHtml, ".html");

						strcpy(urlfolder, nextUrl);
						j = length;
						if(result == 0) {
							while (urlfolder[--j] != '/') urlfolder[j] = 0;
						}

						// put the web content to the content queue
						queue_add(&ContentQueue, (void*) content);
						// put the folder to folder queue
						queue_add(&Folder, (void*) urlfolder);
					}
				}
			}
			if (totalNumberOfWebPages >= MAX_PAGE) { // if more than max page break out
				break;
			}
		}

		isGetContentThreadProcessing = 0; // set to false
	// looping if thread is processing or queues are not empty
	} while ( isScanForURLsThreadProcessing || queue_isEmpty(&ContentQueue) == 0 || queue_isEmpty(&UrlQueue) == 0 );

	return NULL;
}

void* ScanForURLsThreadMethod(void* args) {
	// Pick content from the content queue.
	// Scan for URLs in the content and put to the URL queue.
    do {
    		//CurrentURLFolderAndItsContentType nextContent; // next folderAndcontent
    		char* nextContent;
    		char* folder;
              isScanForURLsThreadProcessing = 1;
              if (queue_isEmpty(&ContentQueue) == 0) {
             // if (ContentQueue.TryDequeue(out nextContent))// If there is an item in the queue ready to process.
            	  nextContent = (void*)queue_remove(&ContentQueue);
            	  folder = (void*)queue_remove(&Folder);
                  GetHTMLURLsAndPutToQueue(nextContent, folder);
              }

              isScanForURLsThreadProcessing = 0;
     // queue is not empty return 0
	}  while (isGetContentThreadProcessing || queue_isEmpty(&ContentQueue) == 0 || queue_isEmpty(&UrlQueue) == 0 );


	return NULL;
}

// get urls and put in to urlQueue
void GetHTMLURLsAndPutToQueue(char* nextContent, char* folder1) {

	char* content = nextContent; // actual content in a page
	char* folder = folder1;
	char* potentialURLs[200];
	int pSize = 0;


	char* st1 = strstr(content, "<a href="); // only care the start of this string and end with ">
	while (st1 != NULL)
	{
		char* st2 = strstr(st1, "\">");

		if (st2 != NULL)
		{
			char* url = malloc(1000);
			memset(url, 0, 1000);
			strncpy(url, st1 + 9, st2 - (st1 + 9));

			char* temp = malloc(1000);
			memset(temp, 0, 1000);
			strcpy(temp, url);
			// add url to list
			potentialURLs[pSize] = temp;
			pSize++; // increase size of potentiaUrls by 1
		}
		else
		{
			st2 = st1 + 1;
		}

		st1 = strstr(st2, "<a href=");
	}

	// process potential urls list
	 if (pSize > 1) {

		 int i;
		 for (i = 0; i < pSize; i++) {
			 char* potentialURL = potentialURLs[i];
			 char* url = malloc(1000);
			 memset(url, 0, 1000);

			 if (!strstr(potentialURL, "http://"))
			 {
				 strcat(url, folder); // append folder to the end of url
			 }

			 strcat(url, potentialURL); // append specific/ potential url in the folder above

			 totalNumberOfURLs++;
			 queue_add(&UrlQueue, url);
		 }
	 }
}


