/* 
 * File:   Printer.h
 * Author: griver
 *
 * Created on 12 Декабрь 2011 г., 12:23
 */

#ifndef PRINTER_H
#define	PRINTER_H

#include "Operations.h"


class Printer
{
public:
    void operator() (vector<Operation*> const & data) const;
}



#endif	/* PRINTER_H */

