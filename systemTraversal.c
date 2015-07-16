/*
 * Viet Nguyen
 * TCSS 422
 * Autumn 2014
 * Homework 4
 * Version 2.0
 */

#include <dirent.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>

int processPath(char x[]); // declare function processPath
int main(int argc, char* argv[]) {

	DIR *pDir;
	pDir = opendir(argv[1]);
	// handling bad input , incorrect path, or non existed path/directory
	if (pDir == NULL) {
			perror ("Error, please check your input " );
		    exit (1);
	}

	// function call, processing path
	processPath( argv[1]);

	return EXIT_SUCCESS;
}
/*
 * This function take an argument as a path and process its contents to return the size and names of
 * sub paths if they exist
 */
int processPath( char *arg) {

	struct stat buf;  // Initial stat struct
	struct stat lbuf; // initial stat struct for symbolic link type
	stat(arg, &buf); // capture information to buf
	lstat(arg, &lbuf); // capture information to sybolic link buf
	off_t size; // initial size of file or symbolic link

	// 1st base case when file is symbolic link
	if (S_ISLNK(lbuf.st_mode) != 0) {

		size = lbuf.st_size;
		printf("% 12d  %s\n" , size, arg ); // output information of symbolic link file

		return size;

	// 2nd base case when is a file
	} else if (S_ISDIR(buf.st_mode) == 0) {

		size = buf.st_size;
		printf("% 12d  %s\n" , size, arg );// output information file

		return size;

	// recursive case if is a folder
	} else {
		struct dirent *pDirent;
		DIR *pDir;
		pDir = opendir(arg);
		int size = (int) buf.st_size;

		// processing all files, folders in the folder
		while ((pDirent = readdir(pDir)) != NULL) {
			char direct [1024] = "";
			char* temp = pDirent->d_name; // content in the folder

			if (strcmp(temp, ".") != 0 && strcmp(temp, "..") != 0 ) { // filter "." and ".."
				strcpy(direct, arg);
				strcat (direct, "/");
				strcat(direct, temp); // append parent path to current files/folder
				// then recursive call with the new path argument
				size += processPath(direct);
			}
		}

		printf("% 12d  %s\n" , size, arg); // out put of folder

		closedir(pDir); // close directory when done to avoid memory issue
		return size;
	}
}
