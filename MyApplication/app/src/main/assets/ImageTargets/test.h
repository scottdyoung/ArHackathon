/*
created with obj2opengl.pl

source file    : .\test.obj
vertices       : 918
faces          : 482
normals        : 228
texture coords : 918


// include generated arrays
#import ".\test.h"

// set input data to arrays
glVertexPointer(3, GL_FLOAT, 0, testVerts);
glNormalPointer(GL_FLOAT, 0, testNormals);
glTexCoordPointer(2, GL_FLOAT, 0, testTexCoords);

// draw data
glDrawArrays(GL_TRIANGLES, 0, testNumVerts);
*/

unsigned int testNumVerts = 1446;

float testVerts [] = ;

float testNormals [] = ;

float testTexCoords [] = ;

