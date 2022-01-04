import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'searchFilter' })
export class SearchFilterPipe implements PipeTransform {

  transform(items: any[], searchText: string, prop: string): any[] {
    if (!items) {
      return [];
    }
    if (!searchText || !prop) {
      return items;
    }
    searchText = searchText.toLocaleLowerCase();

    return items.filter((item) =>
      item[prop].toString().toLowerCase().includes(searchText.toLowerCase()));
  }
}
